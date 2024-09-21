console.log("contacts.js loaded");

var contactModal = document.getElementById("view_contact_modal");

console.log(contactModal);

// options with default values
const contactsOptions = {
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
const contactInstanceOptions = {
  id: 'view_contact_modal',
  override: true
};

var contact = new Modal(contactModal, contactsOptions, contactsOptions);

function showContact(c){

    // var contactID = document.getElementById("contact_id");
    var contactName = document.getElementById("contact_name");
    var contactEmail = document.getElementById("contact_email");
    var contactImage = document.getElementById("contact_image");
    var contactPhone = document.getElementById("contact_phone");
    var contactPhone = document.getElementById("contact_phone");
    var contactAddress = document.getElementById("contact_address");
    var contactDescription = document.getElementById("contact_description");
    var contactWebsite = document.getElementById("contact_website");
    var contactLinkedIn = document.getElementById("contact_linkedIn");

    // contactID.innerHTML=c.id;
    contactName.innerHTML = c.name;
    contactEmail.innerHTML = c.email;
    contactImage.src = c.picture;
    contactPhone.innerHTML=c.phoneNumber;
    contactPhone.innerHTML=c.phoneNumber;
    contactAddress.innerHTML=c.address;
    contactDescription.innerHTML=c.description;
    contactWebsite.href=c.websiteLink;
    contactLinkedIn.href=c.linkedInLink;


    console.log(contactImage);
    console.log(c.id);
    
    contact.show();
}

function hideContact(){
    contact.hide();
}