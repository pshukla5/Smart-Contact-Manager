console.log("script loaded");

let currentTheme = localStorage.getItem("theme") ? localStorage.getItem("theme") : "light";

// calling change theme only when content is perfectly loaded

document.addEventListener("DOMContentLoaded", () =>{

    changeTheme();
});

function changeTheme(){

    // set to web
    document.querySelector('html').classList.add(currentTheme);

    // set listener
    const change_theme_button = document.querySelector("#theme_change_button");
    change_theme_button.querySelector('span').textContent = currentTheme == "light" ? "Dark":"Light";


    change_theme_button.addEventListener("click", (event) =>{

        console.log("change theme button clicked");

        const oldTheme = currentTheme;
        const newTheme = currentTheme === "light" ? "dark" : "light";

        // updating current theme
        currentTheme = newTheme;

        // updating html to new theme
        document.querySelector('html').classList.remove(oldTheme);
        document.querySelector('html').classList.add(newTheme);

        // updating local storage
        localStorage.setItem("theme",newTheme);

        // changing the text of button
        change_theme_button.querySelector('span').textContent = newTheme === "light" ? "Dark":"Light";

    });
}