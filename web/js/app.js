/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// // const leftSidebar = document.querySelector('.left-sidebar');
// //         const rightSidebar = document.querySelector('.right-sidebar');
const leftNav = document.querySelector('.left-nav')

window.addEventListener('scroll', () => {
    // Kiểm tra vị trí cuộn
})

function actionHover() {
    let ProductList = document.querySelectorAll('.product-item')
    ProductList.forEach((product) => {
        product.addEventListener('mouseover', () => {
           console.log('hover')
            product.querySelector('.button-products').classList.add('activeHover')
        })
        product.addEventListener('mouseout', () => {
            product.querySelector('.button-products').classList.remove('activeHover')
        })
    })
}
actionHover()

// const rightBtn = document.getElementById("right-arrow")
// const leftBtn = document.getElementById("left-arrow")
// const listImage = document.querySelectorAll(".list-image")[0]
// const mainImg = document.getElementById("main-image");
// rightBtn.addEventListener("click", () => {
//     listImage.scrollLeft += 140;
// })
// leftBtn.addEventListener("click", () => {
//     listImage.scrollLeft -= 140;
// })

// const imageItem = document.querySelectorAll('.image-item')
// imageItem.forEach((item, currentIndex) => {
//         item.addEventListener('mouseover', () => {
//             console.log(currentIndex);
//             let itemImage = item.getElementsByTagName('img')[0]

//             for (let i = 0; i < imageItem.length; i++) {
//                 if (imageItem[i].classList.contains('onmouse')) {
//                     if (currentIndex != i) {
//                         imageItem[i].classList.remove('onmouse')
//                         break;
//                     }
//                 }
//             }

//             item.classList.add('onmouse')
//             mainImg.src = itemImage.src
//         })
//     })
// increase, decrease number product order
const addNumberBtn = document.getElementById('addNumberBtn')
const minusNumberBtn = document.getElementById('minusNumberBtn')
const numberValue = document.getElementById('numberValue')
const numberAvailable = document.getElementById('number-available')
    //avoid more than available
addNumberBtn.addEventListener('change', () => {
    if (parseInt(numberValue.value) > parseInt(numberAvailable.innerText)) {
        numberValue.value = parseInt(numberAvailable.innerText);
    }
})
minusNumberBtn.addEventListener('click', () => {
    if ((parseInt(numberValue.value) - 1) > 0) {
        numberValue.value = (parseInt(numberValue.value) - 1);
    }
})
addNumberBtn.addEventListener('click', () => {
    if ((parseInt(numberValue.value) + 1) <= parseInt(numberAvailable.innerText)) {
        numberValue.value = (parseInt(numberValue.value) + 1);
    }
})

    //color choose
const colorItem = document.querySelectorAll('.color-item')
colorItem.forEach((item, index) => {
    item.addEventListener('click', () => {
        for (let i = 0; i < colorItem.length; i++) {
            if (colorItem[i].classList.contains('choose') && i != index) {
                colorItem[i].classList.remove('choose')
                break;
            }
        }
        item.classList.add('choose')
    })
})