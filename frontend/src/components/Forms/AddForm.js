import React from 'react'

const AddForm = ({addAddress, newName, handleNameChange, newStreet, handleStreetChange, newCity, handleCityChange, newZipcode, handleZipcodeChange}) => {
    return(
        <div>
            <h2>Add an address</h2>
            <form onSubmit={addAddress}>
                <div><label className="AddLabel">Name:</label><input value={newName} onChange={handleNameChange}/></div>
                <div><label className="AddLabel">Street:</label><input value={newStreet} onChange={handleStreetChange}/></div>
                <div><label className="AddLabel">City:</label><input value={newCity} onChange={handleCityChange}/></div>
                <div><label className="AddLabel">Zipcode:</label><input value={newZipcode} onChange={handleZipcodeChange}/></div>
                <div><button className="SubmitButton" type="submit">add</button></div>
            </form>
        </div>
    )
}

export default AddForm