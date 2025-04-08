document.addEventListener("DOMContentLoaded", function () {
    const popup = document.getElementById("popup");
    const showPopupBtn = document.getElementById("showPopup");
    const closePopupBtn = document.getElementById("closePopup");

    showPopupBtn.addEventListener("click", function () {
        popup.style.display = "flex";
    });

    closePopupBtn.addEventListener("click", function () {
        popup.style.display = "none";
    });
});
