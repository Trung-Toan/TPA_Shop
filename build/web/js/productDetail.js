/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function addMoreProductDetail() {
    var root = document.querySelector('.root');

    var productDetailDiv = document.createElement('div');
    productDetailDiv.className = "mb-3 row";

    var sizeDiv = document.createElement('div');
    sizeDiv.className = "col-md-4";

    var sizeLabel = document.createElement('label');
    sizeLabel.className = "form-label fs-4";
    sizeLabel.textContent = "Size";

    var sizeInput = document.createElement('input');
    sizeInput.type = "text";
    sizeInput.className = "form-control fs-4 py-3";
    sizeInput.name = "size";
    sizeInput.placeholder = "Size";
    sizeInput.required = true;

    sizeDiv.appendChild(sizeLabel);
    sizeDiv.appendChild(sizeInput);
    productDetailDiv.appendChild(sizeDiv);


    var colorDiv = document.createElement('div');
    colorDiv.className = "col-md-4";

    var colorLabel = document.createElement('label');
    colorLabel.className = "form-label fs-4";
    colorLabel.textContent = "Color";

    var colorInput = document.createElement('input');
    colorInput.type = "text";
    colorInput.className = "form-control fs-4 py-3";
    colorInput.name = "color";
    colorInput.placeholder = "Color";
    colorInput.required = true;

    colorDiv.appendChild(colorLabel);
    colorDiv.appendChild(colorInput);
    productDetailDiv.appendChild(colorDiv);


    var quantityDiv = document.createElement('div');
    quantityDiv.className = "col-md-4";

    var quantityLabel = document.createElement('label');
    quantityLabel.className = "form-label fs-4";
    quantityLabel.textContent = "Quantity";

    var quantityInput = document.createElement('input');
    quantityInput.type = "text";
    quantityInput.className = "form-control fs-4 py-3";
    quantityInput.name = "quantity";
    quantityInput.value = "1";

    quantityDiv.appendChild(quantityLabel);
    quantityDiv.appendChild(quantityInput);
    productDetailDiv.appendChild(quantityDiv);

    root.appendChild(productDetailDiv);
}