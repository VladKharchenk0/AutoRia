const refs = {
    brandSelect: document.querySelector('#carBrand'),
    modelSelect: document.querySelector('#carModel'),
    bodySelect: document.querySelector('#carBody'),
    carBrandList: document.querySelector('.car_brand_list'),
    carModelList: document.querySelector('.car_model_list'),
    carForm: document.querySelector('#carForm'),
    results: document.querySelector('#results')
}

let allCarBrands;
let brandModels;
let isBrandChoosen = false;
let isModelChoosen = false;
const finalObj = {};

const createMarkup = (array) => array.map(el => `<option value=${el.value}>${el.name}</option>`).join('')
const createListMarkup = (array) => array.map(el => `<li data-brand="ok" data-value=${el.value} class="brand_list_el">${el.name}</li>`).join('')
const createListModelMarkup = (array) => array.map(el => `<li data-model="ok" data-value=${el.value} class="brand_list_el">${el.name}</li>`).join('')
const filteredValues = (array, filter) => array.filter(el => el.name.toLowerCase().includes(filter.toLowerCase()))
const resultMarkup = array => {

    if (!array.length) return `<p>No resulst</p>`

    const body = array.map(car => `<div class="car-card">
<div class="img-container"><a href="https://auto.ria.com${car.linkToView}" target="_blank" rel="noopener"><img src='${car.photoUrl}' alt="car photo"/></a></div>
<div class="car-info__container">
<p><a href="https://auto.ria.com${car.linkToView}" target="_blank" rel="noopener">${car.mark} ${car.model}</a></p>
        <p>Price: ${car.price}$</p>
        <p>City: ${car.city}</p>
        
    </div>
  </div>`).join('')


    return body
}
// API

const API_KEY = 'f2rE1cesrpuOT7gzsvL0AY4Itb4pA7O3rV52eYDK'
const BASE_URL = 'https://developers.ria.com/auto/categories/1/'

const getCarBody = () => fetch(`${BASE_URL}bodystyles?api_key=${API_KEY}`)
const getCarBrand = () => fetch(`${BASE_URL}marks?api_key=${API_KEY}`)

getCarBody().then(resp => resp.json()).then(data => {
    const markup = createMarkup(data)
    refs.bodySelect.insertAdjacentHTML('beforeend', markup)
})

getCarBrand().then(resp => resp.json()).then(data => {

    allCarBrands = data
})

const renderCarBrandList = (e) => {
    const filteredData = filteredValues(allCarBrands, e.target.value)
    const markup = createListMarkup(filteredData)
    refs.carBrandList.innerHTML = markup
}

const renderModelList = (e) => {
    if (!brandModels) {
        alert('choose car brand')
        return
    }
    const filteredData = filteredValues(brandModels, e.target.value)
    const markup = createListModelMarkup(filteredData)
    refs.carModelList.innerHTML = markup
}

const getInputValue = (e) => {
    if (e.target === e.currentTarget) return

    if (e.target.dataset.brand === 'ok') {
        refs.brandSelect.value = e.target.innerText
        finalObj.carBrand = e.target.dataset.value
        isBrandChoosen = true

        fetch(`${BASE_URL}marks/${e.target.dataset.value}/models?api_key=${API_KEY}`).then(resp => resp.json()).then(data => {

            brandModels = data
            refs.carBrandList.classList.remove("show")
        })
    }

    if (e.target.dataset.model === 'ok') {
        refs.modelSelect.value = e.target.innerText
        finalObj.carModel = e.target.dataset.value
        isModelChoosen = true
        refs.carModelList.classList.remove("show")
    }

}

const cleanInput = (e) => {
    console.log(e.target)
    if (e.target.dataset.brand !== 'ok') {
        refs.carBrandList.classList.remove("show")
    }

    if (!isBrandChoosen) {
        refs.brandSelect.value = '';
    }

    if (e.target.dataset.model !== 'ok') {
        refs.carModelList.classList.remove("show")
    }

    if (!isModelChoosen) {
        refs.modelSelect.value = '';
    }

}

const openCarBrandList = (e) => {
    if (refs.carBrandList.classList.contains("show")) return
    refs.carBrandList.classList.add("show")
    renderCarBrandList(e)
    refs.carBrandList.addEventListener('click', getInputValue)
}

const openCarModelList = (e) => {
    if (refs.carModelList.classList.contains("show")) return
    if (!brandModels) {
        alert('choose car brand')
        return;
    }
    refs.carModelList.classList.add("show")
    renderModelList(e)
    refs.carModelList.addEventListener('click', getInputValue)
}

const getBodyValue = (e) => {
    finalObj.carBody = e.target.value
}

const onSubmit = (e) => {
    e.preventDefault()
    console.log(finalObj)

    fetch('/index/search', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(finalObj)
    }).then(resp => resp.json()).then(data => {
        const markup = resultMarkup(data)

        refs.results.innerHTML = markup
    })
}

refs.brandSelect.addEventListener('input', renderCarBrandList)
refs.modelSelect.addEventListener('input', renderModelList)
window.addEventListener('click', cleanInput)
refs.brandSelect.addEventListener('click', openCarBrandList)
refs.modelSelect.addEventListener('click', openCarModelList)
refs.bodySelect.addEventListener('change', getBodyValue)
refs.carForm.addEventListener('submit', onSubmit)
