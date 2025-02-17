const messageInput = document.getElementById('message-input');
const sendButton = document.getElementById('send-button');
const messagePage = document.querySelector('.message-page');

function addMessage(content, isOutgoing) {
  const messageElement = document.createElement('div');
  messageElement.classList.add('message', isOutgoing ? 'outgoing' : 'incoming');

  if (isOutgoing) {
    messageElement.innerHTML = `
      <div class="message-content">
        <p>${content}</p>
        <span class="timestamp">${new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}</span>
      </div>
      <img src="images/girl.avif" alt="Avatar" class="message-avatar">
    `;
  } else {
    // Boy's message (left side)
    messageElement.innerHTML = `
      <img src="images/boy.jpg" alt="Avatar" class="message-avatar">
      <div class="message-content">
        <p>${content}</p>
        <span class="timestamp">${new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}</span>
      </div>
    `;
  }

  messagePage.appendChild(messageElement);
  messagePage.scrollTop = messagePage.scrollHeight; 
}

function boyReply(userMessage) {
  let reply = '';

  if (userMessage.toLowerCase().includes('how are you') || userMessage.toLowerCase().includes('how\'s it going')) {
    reply = "I'm doing well, thanks for asking!";
  } else if (userMessage.toLowerCase().includes('plans') || userMessage.toLowerCase().includes('what\'s up')) {
    reply = "I have no plans for now, but I'd love to hang out!";
  } else if (userMessage.toLowerCase().includes('time') || userMessage.toLowerCase().includes('what time is it')) {
    reply = "The current time is " + new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) + ".";
  } else if (userMessage.toLowerCase().includes('fun') || userMessage.toLowerCase().includes('exciting')) {
    reply = "That sounds fun! I'm always up for something exciting!";
  } else if (userMessage.toLowerCase().includes('hello') || userMessage.toLowerCase().includes('hi')) {
    reply = "Hey! How are you doing?";
  } else {
    reply = "Hmm, that's interesting! Tell me more.";
  }
  
  setTimeout(() => {
    addMessage(reply, false); 
  }, 1000);
}

sendButton.addEventListener('click', () => {
  const messageText = messageInput.value.trim();

  if (messageText) {
    addMessage(messageText, true); 
    messageInput.value = ''; 
    boyReply(messageText); 
  }
});

messageInput.addEventListener('keypress', (e) => {
  if (e.key === 'Enter') {
    sendButton.click();
  }
});
