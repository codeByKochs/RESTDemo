import React from 'react'

const AddressEntry = ({addressObject, handleDeleteButtonClick}) => {
    return(
        <li className="AddressEntry">
            {addressObject.name} - {addressObject.street}, {addressObject.city}, {addressObject.zipcode}<button className="DeleteButton" onClick={handleDeleteButtonClick}>delete</button>
        </li>
    )
}

export default AddressEntry