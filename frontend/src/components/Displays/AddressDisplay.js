import React from 'react'
import AddressEntry from './AddressEntry'

// displays addresses in unordered list from
// entrys get filtered before showing
const AddressDisplay = ({addresses, filter, deleteEntry}) => {

    // filters addresses based on filter for all fields, ignores capitalization
    const addressesToShow = 
        filter === '' ? 
        addresses : 
        addresses.filter(
            address => 
            address.name.toLowerCase().includes(filter.toLowerCase()) ||
            address.street.toLowerCase().includes(filter.toLowerCase()) ||
            address.city.toLowerCase().includes(filter.toLowerCase()) ||
            address.zipcode.toLowerCase().includes(filter.toLowerCase())
            )    

    return (
        <div className='AddressDisplay'>
            <ul>
                {addressesToShow.map(
                    addressObject => 
                    <AddressEntry key={addressObject.id} addressObject={addressObject} handleDeleteButtonClick={() => deleteEntry(addressObject.id)} />
                )}
            </ul>
        </div>
    )
}

export default AddressDisplay