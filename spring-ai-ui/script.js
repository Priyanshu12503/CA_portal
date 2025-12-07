document.getElementById("askBtn").addEventListener("click", async () => {
  const question = document.getElementById("promptInput").value.trim();
  const responseBox = document.getElementById("responseBox");

  if (!question) {
    responseBox.textContent = "⚠️ Please enter a question.";
    return;
  }

  responseBox.textContent = "⏳ Thinking...";

  try {
    const res = await fetch("http://localhost:8080/api/chat", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ question }),
    });

    if (!res.ok) {
      throw new Error("Backend error");
    }

    const data = await res.json();
    responseBox.textContent = data.answer || "No response received.";
  } catch (error) {
    responseBox.textContent = "❌ Could not connect to backend.";
  }
});
