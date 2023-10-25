// Fetch menu data from the API
fetch('http://localhost:8080/menu/getMenu')
    .then(response => response.json())
    .then(data => {
        const menuItemsContainer = document.getElementById('menu-items');

        data.forEach(item => {
            const card = document.createElement('div');
            card.className = 'col mb-5';
            card.innerHTML = `
                    <div class="card h-70">
                        <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">${item.itemCategory}</div>
                        <img class="card-img-top" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSIyubInFzNbMdmL9GnP6VUcPgU1yE09LtR4g&usqp=CAU" alt="..." />
                        <div class="card-body p-4">
                            <div class="text-center">
                                <h5 class="fw-bolder">${item.itemName}</h5>
                                <span class="text-muted">$${item.itemPrice.toFixed(2)}</span>
                                <p>${item.itemDescription}</p>
                            </div>
                        </div>
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center">
                                <button class="btn btn-outline-dark mt-auto"${onclick = addToCart(item.itemId,item.itemPrice)}>Add to cart</button>
                            </div>
                        </div>
                    </div>
                `;
            menuItemsContainer.appendChild(card);
        });
    })
    .catch(error => console.error('Error fetching menu data:', error));

class CartItem {
    constructor(itemId, qty, itemPrice) {
        this.itemId = itemId
        this.qty = qty
        this.itemPrice = itemPrice
    }
}
function addToCart(itemId,itemPrice) {
    const newItem=new CartItem()
}