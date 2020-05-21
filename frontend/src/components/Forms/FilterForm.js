import React from 'react'

const FilterForm = ({filter, handleFilterChange}) => {
    return(
        <div className='FilterForm'>
            <label className="FilterFormLabel">Filter addresses </label>
            <input value={filter} onChange={handleFilterChange}></input>
        </div>
    )
}

export default FilterForm