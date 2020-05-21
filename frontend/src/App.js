import React, {useState, useEffect} from 'react';
import AddressService from './service/AddressService'
import AddressDisplay from './components/Displays/AddressDisplay';
import FilterForm from './components/Forms/FilterForm';
import Notification from './components/Displays/Notification';
import AddForm from './components/Forms/AddForm';
import './index.css';

function App() {
  const [addresses, setAddresses] = useState([])
  const [filter, setFilter] = useState("")
  const [message, setMessage] = useState("")
  const [messageType, setMessageType] = useState("")

  const [newName, setNewName] = useState("")
  const [newStreet, setNewStreet] = useState("")
  const [newCity, setNewCity] = useState("")
  const [newZipcode, setNewZipcode] = useState("")

  // imports addresses via addressService
  const importAddresses = () => {
    AddressService
      .getAllAddresses()
      .then(addresses => {
        setAddresses(addresses)
        displayMessage("successMessage", "Data loaded from server successfully")
      })
      .catch(error => {
        console.log("an error occured loading addresses from server");
        displayMessage("errorMessage", "Data could not be loaded from server")
      })
  }
  // useEffect starts importAddresses once, when the page is loaded
  useEffect(importAddresses, [])

  const handleFilterChange = (event) => setFilter(event.target.value)
  const handleNameChange = (event) => setNewName(event.target.value)
  const handleStreetChange = (event) => setNewStreet(event.target.value)
  const handleCityChange = (event) => setNewCity(event.target.value)
  const handleZipcodeChange = (event) => setNewZipcode(event.target.value)


  // adds a new Address to server
  const addAddress = (event) => {
    event.preventDefault()
    const newAddress = {
      name : newName,
      street : newStreet,
      city : newCity,
      zipcode : newZipcode
    }
    // checks if any of the address form fields are empty
    if(newAddress.name === '' || newAddress.street === '' || newAddress.city === '' || newAddress.zipcode === ''){
      alert('please fill out all fields (name, street, city and zipcode)')
      displayMessage("errorMessage", "Address could not be added")
    }
    // checks if address is already included in registered addresses
    else if (addresses.some(
      address => 
        address.name.toLowerCase() === newAddress.name.toLowerCase() &&
        address.street.toLowerCase() === newAddress.street.toLowerCase() &&
        address.city.toLowerCase() === newAddress.city.toLowerCase() &&
        address.zipcode.toLowerCase() === newAddress.zipcode.toLowerCase()
      )){
      alert("This address is already registered under the given name")
      displayMessage("errorMessage", "Address is already registered")
    }
    // if an address in the registered addresses exists with same street, city and zipcode, the name can be updated
    else if(addresses.some(
      address => 
        address.street.toLowerCase() === newAddress.street.toLowerCase() &&
        address.city.toLowerCase() === newAddress.city.toLowerCase() &&
        address.zipcode.toLowerCase() === newAddress.zipcode.toLowerCase()
      )){
        if (window.confirm((`A name is already registered under ${newAddress.street}, ${newAddress.city}, ${newAddress.zipcode}. Would you like to update the registered name`))){
          // finding address object, which is supposed to be updated
          const toBeUpadted = addresses.find(
            address => 
              address.street === newAddress.street &&
              address.city === newAddress.city &&
              address.zipcode === newAddress.zipcode)
          // requesting update in address service
          AddressService
          .update(newAddress, toBeUpadted.id)
          .then(response => {
            setAddresses(addresses.map(address => address.id !== toBeUpadted.id ? address : response))
            displayMessage("successMessage", "Address updated")
          })
          .catch( error => {
            console.log("Failed to update address");
            setAddresses(addresses.filter(address => address.name !== newAddress.name))
            displayMessage("errorMessage", "Address data could not be updated")
          })
        }
      }
    // address is added to registered addresses
    else {
      AddressService
      .create(newAddress)
      .then(returnedAddress => {
        setAddresses(addresses.concat(returnedAddress))
        setNewName('')
        setNewStreet('')
        setNewCity('')
        setNewZipcode('')
        console.log("successfully added address");
        displayMessage("successMessage", "Added new Address")
      })
      .catch( error => {
        console.log("Failed to create new Person");
        displayMessage("errorMessage", "Address could not be added")
      })
    }
  }

  const deleteEntry = (id) => {
    const toBeDeleted = addresses.find(address => address.id === id)
    if (window.confirm(`Delete entry under name ${toBeDeleted.name}?`)){
      AddressService.erase(id)
      .then(response => {
        setAddresses(addresses.filter(address => address.id !== toBeDeleted.id))
        displayMessage("successMessage", "Address deleted successfully")
      })
      .catch(error => {
        console.log("failed to delete address");
        displayMessage("errorMessage", "Address could not be deleted")
      })
    }
  }

  const displayMessage = (messageType, message) => {
    setMessage(message)
    setMessageType(messageType)
    setTimeout(() => {
      setMessage(null)
      setMessageType(null)
    }, 5000)
  }

  return (
    <div className="App">
      <h1>Address service</h1>
      <Notification message={message} messageType={messageType} />
      <FilterForm filter={filter} handleFilterChange={handleFilterChange}/>
      <AddForm addAddress={addAddress} newName={newName} handleNameChange={handleNameChange} 
                                      newStreet={newStreet} handleStreetChange={handleStreetChange}
                                      newCity={newCity} handleCityChange={handleCityChange}
                                      newZipcode={newZipcode} handleZipcodeChange={handleZipcodeChange}/>
      <hr />
      <AddressDisplay addresses={addresses} filter={filter} deleteEntry={deleteEntry}/>
    </div>
  );
}

export default App;