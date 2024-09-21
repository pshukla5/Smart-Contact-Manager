console.log("delete.js loaded");

var deleteModal = document.getElementById("delete_contact");

console.log(deleteModal);

// options with default values
const deleteOptions = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const deleteInstanceOptions = {
  id: 'delete_contact',
  override: true
};

var delete_contact = new Modal(deleteModal, deleteOptions, deleteInstanceOptions);

function showDeleteModal(contactId){

    var deleteHref = document.getElementById("delete_href");
    deleteHref.href = "/user/contacts/delete/" + contactId;
    
    delete_contact.show();
}

function hidedeleteModal(){
    delete_contact.hide();
}