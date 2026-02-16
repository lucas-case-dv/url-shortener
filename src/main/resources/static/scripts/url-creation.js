function shortenURL() {
    const url = document.getElementById('url-input').value;

    if (url === '') {
        alert('Please enter a valid URL');
        return;
    }

    fetch('/api/urls', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ originalUrl: url })
    })
        .then(response => response.json())
        .then(data => {
            const resultDiv = document.getElementById('result');
            const currentOrigin = window.location.origin;
            resultDiv.innerHTML = `
            <p class="url-shortened">
              <a href="${currentOrigin}/${data.shortCode}" target="_blank">
                ${currentOrigin}/${data.shortCode}
              </a>
            </p>`;
        })
        .catch(error => console.error('Error:', error));
}