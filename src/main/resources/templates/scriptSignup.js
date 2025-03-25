document.getElementById("signupForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const loginData = {
        username: username,
        email: email,
        password: password
    };

    try {
        const response = await fetch("http://localhost:8080/api/v1/auth/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        });

        const data = await response.json();

        if (response.ok) {
            document.getElementById("message").innerText = "Signup successed!";
            localStorage.setItem("token", data.token);
            window.location.href = "login.html";
        } else {
            document.getElementById("message").innerText = "Signup failed: " + (data.message || "Please check again you username and password");
        }
    } catch (error) {
        console.error("Error:", error);
        document.getElementById("message").innerText = "there are error, please try again letter.";
    }
});