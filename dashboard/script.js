document.addEventListener("DOMContentLoaded", function() {
    // document.getElementById("menu-toggle").addEventListener("click", function(e) {
    //     e.preventDefault();
    //     document.getElementById("wrapper").classList.toggle("toggled");
    // });

    // Sample data for demonstration purposes
    const ordersData = {
        totalOrdersToday: 150,
        pendingOrders: 5,
        completedOrders: 130,
        cancelledOrders: 15
    };

    // Fake order details data
    const fakeOrderDetails = [
        { date: '2024-05-01', orderId: 'ORD123', totalAmount: '$50', mode: 'dine in', serverName: 'John Doe' },
        { date: '2024-05-02', orderId: 'ORD124', totalAmount: '$30', mode: 'delivery', serverName: 'Jane Smith' },
        { date: '2024-05-03', orderId: 'ORD125', totalAmount: '$40', mode: 'dine in', serverName: 'Michael Brown' }
    ];

    // Function to create an order card
    function createOrderCard(title, count, borderColorClass) {
        return `
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card ${borderColorClass}">
                    <div class="card-body">
                        <h5 class="card-title">${title}</h5>
                        <p class="card-text">${count}</p>
                    </div>
                </div>
            </div>
        `;
    }

    // Generate HTML for orders overview
    const ordersOverviewHTML = `
        ${createOrderCard('Total Orders Today', ordersData.totalOrdersToday, 'border-left-primary')}
        ${createOrderCard('Pending Orders', ordersData.pendingOrders, 'border-left-warning')}
        ${createOrderCard('Completed Orders', ordersData.completedOrders, 'border-left-success')}
        ${createOrderCard('Cancelled Orders', ordersData.cancelledOrders, 'border-left-danger')}
    `;

    // Inject the generated HTML into the orders overview section
    document.getElementById('content').innerHTML = ordersOverviewHTML;

    // Function to create a list item with a dropdown for order details
    function createOrderListItem(title, count, detailsId) {
        const orderDetailsHTML = fakeOrderDetails.map(detail => `

            <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Sno.</th>
                <th scope="col">Date</th>
                <th scope="col">Order ID</th>
                <th scope="col">Total amount</th>
                <th scope="col">Mode</th>
                <th scope="col">Server Name</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>${detail.date}</td>
                <td>${detail.orderId}</td>
                <td>${detail.totalAmount}</td>
                <td>${detail.mode}</td>
                <td>${detail.serverName}</td>
            </tr>
        `).join('');
        
        return `
            <li class="list-group-item d-flex justify-content-between align-items-center order-summary-item">
                <div class="dropdown-header" data-toggle="collapse" data-target="#${detailsId}" aria-expanded="false" aria-controls="${detailsId}">
                    ${title}
                    <span class="badge badge-primary badge-pill">${count}</span>
                </div>
                <div class="collapse" id="${detailsId}">
                    <ul class="list-group mt-2">
                        ${orderDetailsHTML}
                    </ul>
                </div>
            </li>
        `;
    }

    // Generate HTML for orders list
    const ordersListHTML = `
        <div class="col-lg-12 mb-4">
            <div class="card bg-light">
                <div class="card-body">
                    <h5 class="card-title">Order Summary</h5>
                    <ul class="list-group">
                        ${createOrderListItem('Total Orders Today', ordersData.totalOrdersToday, 'totalOrdersDetails')}
                        ${createOrderListItem('Pending Orders', ordersData.pendingOrders, 'pendingOrdersDetails')}
                        ${createOrderListItem('Completed Orders', ordersData.completedOrders, 'completedOrdersDetails')}
                        ${createOrderListItem('Cancelled Orders', ordersData.cancelledOrders, 'cancelledOrdersDetails')}
                    </ul>
                </div>
            </div>
            
        </div>
        <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Sno.</th>
                        <th scope="col">Date</th>
                        <th scope="col">Order ID</th>
                        <th scope="col">Total amount</th>
                        <th scope="col">Mode</th>
                        <th scope="col">Server Name</th>
                    </tr>
                </thead>
            <table/>
    `;

    // Event listener for the Orders link
    document.getElementById('orders-link').addEventListener('click', function(e) {
        e.preventDefault();
        document.getElementById('content').innerHTML = ordersListHTML;
    });

    // Event listener for the Dashboard link
    document.getElementById('dashboard-link').addEventListener('click', function(e) {
        e.preventDefault();
        document.getElementById('content').innerHTML = ordersOverviewHTML;
    });

    // Initial load
    document.getElementById('content').innerHTML = ordersOverviewHTML;
});
