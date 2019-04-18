var textBoxSelector = document.getElementsByClassName('searchIdInput')[0];
var searchHis = document.getElementById('searchHistory');
var tempTextBox = jQuery("<input />").attr({ type: 'text', id: 'tempbox' });
var rowsSelector = "#text-rows";
$('.text-content').hide();

jQuery( document ).ready(function() {
  registerTextBoxEnterEvent("",jQuery(textBoxSelector),moveText);
  /**
  When storage changes we synchronize the view
  */
 chrome.storage.onChanged.addListener(function(changes, namespace) { // Remove this event once refresh issue fixed (like DELETE)
  console.log('chrome storage changed');

  for (key in changes) {
    if(key === 'entries') {
      console.log(key+' in storage changed:'+JSON.stringify(changes[key]));
      refreshAndFocus();
    }
  }
  });
  jQuery(rowsSelector).on('click', '.delete', function(event) {
    event.preventDefault();
    deleteRow(jQuery(this));
  });
  jQuery(rowsSelector).on('click', '.link', function(event) {
    alert();
    event.preventDefault();
    copyText(jQuery(this));
  });
  $('.showHistory').change(function() {
    if($(this).is(":checked")) {
      $('.text-content').show();
    }else{
      $('.text-content').hide();
    }
  });
  jQuery(".menu-link").on('click', function(event) {
    var anchor = this;

    getEntriesFromStorage(function(result) {
      var entries = result != null ? (result['entries'] != null ? result['entries'] : []) : [];
      console.log('exporting:'+JSON.stringify(entries));
      anchor.onclick = function() {
          anchor.href = 'data:application/json;charset=utf-8,' + encodeURIComponent(JSON.stringify(entries));
          anchor.download = 'temptext.json';
      };
    });
  });
  refreshAndFocus();
});

jQuery(window).bind("load", function() {
  jQuery('html').height(50);
});

function refreshAndFocus() {
    refresh();
    jQuery(textBoxSelector).focus();
}

/**
Moves text from the text box to the top of the list of shown entries, by adding it
 to the chrome storage which will trigger the view refresh
*/
function moveText() {
  var targetText = jQuery(textBoxSelector).val();
  console.log(targetText);

  if (/\S/.test(targetText)) {
    removeAndClearTempBox(); // this clears and reset currently being edited entry
    putEntry(targetText);
  }

  jQuery(textBoxSelector).val("");
}

function deleteRow(element) {
  var rowDiv = element.closest('.row');
  var id = rowDiv.attr('id');
  pullEntry(id);
}

function copyText(element) {
  console.log(element);
  var textBox = jQuery(textBoxSelector);
  var previousTextInBox = textBox.val();  // if box has text we need to recover it after copying
  var rowDiv = element.closest('.row');
  var textSpan = jQuery('#'+rowDiv.attr('id')+' > .text-row')[0];
  textBox.val(textSpan.textContent);
  textBox.select();

  try {
    document.execCommand("copy");
  } catch(e) {
    console.log("Problem while copying text to clipboard. "+e);
  }

  textBox.val(previousTextInBox);

  animateCopy(rowDiv);
}

function animateCopy(rowDiv) {
  if(rowDiv.hasClass('animate-copy')) {
    var newone = rowDiv.clone(true);
    rowDiv.before(newone);
    rowDiv.remove();
  }

  else {
    rowDiv.addClass('animate-copy');
  }
}

/**
Refreshes the view from the chrome storage entries array
*/
function refresh() {
    console.log('refresh');
    var rows = jQuery(rowsSelector);
    rows.empty();
  
    getEntriesFromStorage(function(result) {
      var entries = result != null ? (result['entries'] != null ? result['entries'] : []) : [];
      console.log('storage:'+JSON.stringify(entries));
      var rows = jQuery(rowsSelector);
  
      for(var i=0; i<entries.length; i++) {
        prependRow(entries[i]["id"], entries[i]["value"], rows);
      }
    });
  }

/**
Adds a row to the div container to be displayed in the view, it also makes sure that double click event is handled for
 supporting inline editing of a text entry
*/
function prependRow(uniqueId, text, rows) {
    var deleteIcon = buildDeleteIcon();
    var copyIcon = buildLinkIcon();
    var newRow = jQuery("<div class='row' id="+ uniqueId +">" + deleteIcon + copyIcon+"</div>");
    registerInlineTextBox(newRow);
    var textSpan = jQuery("<span class='text-row' id='text"+uniqueId+"' />").text(text);
    newRow.append(textSpan);
    rows.prepend(newRow);
}

/**
Registers a double click event handler on the row div for supporting editable text entry via a
dynamically created text box appended to the div
*/
function registerInlineTextBox(rowDiv) {
    rowDiv.dblclick(function() {
      var currentTempBoxId = extractUniqueIdFromTempBox();
  
      if(currentTempBoxId !== '') { // another entry is currently being edited so dismiss this event
        console.log('another entry is being edited');
      }else {
        var uniqueId = rowDiv.attr('id');
        var entryValue = jQuery("#text"+uniqueId).text();
        console.log('double click:'+uniqueId);
        tempTextBox.val(entryValue);
        tempTextBox.appendTo(rowDiv);
        registerTextBoxEnterEvent(entryValue, tempTextBox, editTempTextBoxCallback);
        jQuery("#text"+uniqueId).empty();
        tempTextBox.attr('id', 'tempbox'+uniqueId); // to be used by the processEdited method
      }
  
      tempTextBox.focus();
    });
}

/**
Tries to extract the unique id of the previously edited div used for returning previous value to old div
 if the user double clicks other entry before saving currently edited one
*/
function extractUniqueIdFromTempBox() {
  var uniqueId = tempTextBox.attr('id').split('tempbox').pop();
  return uniqueId;
}

function editTempTextBoxCallback(previousValue) {
  var uniqueId = extractUniqueIdFromTempBox();
  var text = tempTextBox.val();

  if (/\S/.test(text) && uniqueId !== '') {
    jQuery("#text"+uniqueId).text(tempTextBox.val());
  }

  else {
    jQuery("#text"+uniqueId).text(previousValue);
  }

  removeAndClearTempBox();
  updateEditedEntry(uniqueId);
}

function removeAndClearTempBox() {
  tempTextBox.attr('id', 'tempbox');
  tempTextBox.val("");
  tempTextBox.remove();
}

function registerTextBoxEnterEvent(previousValue, textBox, callback) {
  console.log('Enter event has been registered')
    textBox.off();
    textBox.on('keypress', function (event) {
      if(event.which === 13){
        if(event.currentTarget.className === 'searchIdInput'){
          callback(previousValue);
        }else{
          callback(previousValue);
        }
      }
    });
}
/**
Constructs a string with the html for displaying the delete icon
*/
function buildDeleteIcon() {
    return "<span><a href='#' class='delete'><img src='deleteIcon.png' alt='delete' title='delete' /></a>&nbsp;</span>";
  }
  
  /**
  Constructs a string with the html for displaying the copy icon
  */
  function buildLinkIcon() {
    return "<span><a href='#' class='copy'><img src='restoreIcon.png' alt='copy' title='copy to clipboard' /></a>&nbsp;</span>";
  }
  
/**
Tries to extract the unique id of the previously edited div used for returning previous value to old div
 if the user double clicks other entry before saving currently edited one
*/
function extractUniqueIdFromTempBox() {
    var uniqueId = tempTextBox.attr('id').split('tempbox').pop();
    return uniqueId;
}

//textBoxSelector.addEventListener("keydown", function(event) {
//    if (event.key === "Enter") {
//      moveToHistory();
//        chrome.tabs.create({'url': "https://www.google.co.in/"}, function(tab) {
//            alert("Tab Opened");
//          });
 //       //window.open("https://www.google.co.in/");
//    }
//});

function moveToHistory(){
    var targetText = textBoxSelector.value;
    if (/\S/.test(targetText)) {
        removeAndClearTempBox(); // this clears and reset currently being edited entry
        putEntry(targetText);
    }
}

function removeAndClearTempBox() {
    tempTextBox.attr('id', 'tempbox');
    tempTextBox.val("");
    tempTextBox.remove();
}

/**
 * Puts an entry into the entries array in chrome storage
 * */
function putEntry(value){
    getEntriesFromStorage(function(result){
        var entries = result != null ? (result['entries'] != null ? result['entries'] : []) : [];
        console.log('storage:'+JSON.stringify(entries));
        var uniqueId = Math.floor(Math.random() * 26) + Date.now();
        var entry = {"id": uniqueId, "value": value};
        entries.push(entry);
        setEntriesToStorage(entries);
    });
}

/**
Sets the whole entries array in the chrome storage
*/
function setEntriesToStorage(entries) {
    chrome.storage.sync.set({'entries': entries}, function() {
      console.log('entries saved to local storage');
      console.log(JSON.stringify(entries));
    });
 }

/**
Gets entries from the chrome storage
*/
function getEntriesFromStorage(callback) {
    chrome.storage.sync.get('entries', callback);
}
//searchHis.addEventListener('click', hello);
//function hello() {
    //window.close();
    //chrome.browserAction.getPopup({
        //popup: "popup1.html"
    //});
    //chrome.tabs.create({'url': "chrome.extension.getURL('popup1.html')"}, function(tab) {
        // Tab opened.
      //});
    //chrome.tabs.executeScript({
     // file: 'history.js'
    //}); 
  //}
  function updateEditedEntry(uniqueId) {
    getEntriesFromStorage(function(result) {
      var entriesFromStorage = result != null ? (result['entries'] != null ? result['entries'] : []) : [];
  
      for(var i=0; i<entriesFromStorage.length; i++) {
        var index = findInEntries(entriesFromStorage, uniqueId);
        var editedEntry = retrieveEntry(uniqueId);
  
        if (editedEntry.value != "") {
          if(index!=null) {
            entriesFromStorage[index] = editedEntry;
          }
  
          else {
            entriesFromStorage.push(editedEntry);
          }
        }
      }
  
      setEntriesToStorage(entriesFromStorage);
    });
  }
  
  function retrieveEntry(uniqueId) {
    var value = jQuery('#text'+uniqueId).text();
    var entry = {"id": uniqueId, "value": value};
    return entry;
  }
  
  function pullEntry(id) {
    getEntriesFromStorage(function(result) {
      var entries = result != null ? (result['entries'] != null ? result['entries'] : []) : [];
      var index = findInEntries(entries, id);
      //jQuery('#'+id).remove(); Change in Future for delete operation
      entries.splice(index, 1);
      setEntriesToStorage(entries);
    });
  }
  
  function findInEntries(entries, id) {
    for(var i=entries.length; i--;) {
      if(entries[i]["id"] == id) {
        return i;
      }
    }
  
    return null;
  }