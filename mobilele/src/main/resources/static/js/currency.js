let currencyDropDown = document.getElementById('currency')

currencyDropDown.addEventListener('change', currencyChanged)

function currencyChanged() {

  // get the selected currency from the dropdown
  let selectedCurrency = currencyDropDown.value
  let amount = document.getElementById('priceInBGN').value
  let priceSpan = document.getElementById('price')

  // write a fetch request to the server to get the exchange rate
  fetch('/api/convert?' + new URLSearchParams({
    from: 'BGN',
    to: selectedCurrency,
    amount: amount
  }))
  .then(response => response.json())
  .then(data => {priceSpan.textContent = data.result})
  .catch(error => {
    console('An error occurred: ' + error)
    priceSpan.textContent = 'Error occurred!'
  })
}


