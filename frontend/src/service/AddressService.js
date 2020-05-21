import axios from 'axios'

const baseUrl = 'http://localhost:8080'

const getAllAddresses = () => {
    // using , produces space between 8080 and /addresses
    const request = axios.get((baseUrl+"/api/addresses"))
    return request.then(response => 
        response.data)
}

const create = (newAddressObject) => {  
    const request = axios.post(baseUrl+"/api/addresses", newAddressObject)
    return request.then(response => response.data)
}

const update = (newAddressObject, id) => {
    const request = axios.put(`${baseUrl}/api/addresses/${id}`, newAddressObject)
    return request.then(response => response.data)
}

const erase = (id) => {
    const request = axios.delete(`${baseUrl}/api/addresses/${id}`)
    return request.then(response => response)
}

export default {getAllAddresses,
                create,
                update,
                erase}