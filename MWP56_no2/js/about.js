document.addEventListener("DOMContentLoaded", function () {
    const aboutContainer = document.getElementById("about");

    if (aboutContainer) {
        aboutContainer.innerHTML = ` `;

        document.getElementById("promoBtn").addEventListener("click", function () {
            document.getElementById("promoPopup").style.display = "flex";
        });

        document.getElementById("closePopup").addEventListener("click", function () {
            document.getElementById("promoPopup").style.display = "none";
        });
    }
});
