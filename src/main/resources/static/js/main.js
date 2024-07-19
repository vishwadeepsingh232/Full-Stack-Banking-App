

//Get Transaction Type:
const transactType=document.querySelector("#transact-type");

//Get Transaction Forms:
const paymentCard =document.querySelector(".payment-card");
const transferCard=document.querySelector(".transfer-card");
const depositeCard=document.querySelector(".deposite-card");
const withdrawCard=document.querySelector(".withdraw-card");


transactType.addEventListener("change",function(){

    //check For Transaction Type and Display Form:
    switch(transactType.value){
        case "payment":
            paymentCard.style.display="block";
            paymentCard.nextElementSibling.style.display="none";
            depositeCard.style.display="none";
            withdrawCard.style.display="none";
        break;

        case "transfer":
            transferCard.previousElementSibling.style.display = "none";
            transferCard.style.display = "block";
            transferCard.nextElementSibling.style.display = "none";
            withdrawCard.style.display="none";
        break;

         case "deposit":
            paymentCard.style.display="none";
            depositeCard.previousElementSibling.style.display = "none";
            depositeCard.style.display = "block";
            depositeCard.nextElementSibling.style.display = "none";

        break;

         case "withdraw":
            withdrawCard.previousElementSibling.style.display = "none";
            withdrawCard.style.display = "block";
            transferCard.style.display="none";
            paymentCard.style.display="none";


        break;
    }


});