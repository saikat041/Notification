importScripts('https://www.gstatic.com/firebasejs/3.9.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/3.9.0/firebase-messaging.js');

var config = {
  apiKey: "AIzaSyADV8acVN_XHD8feh3N6xGORW40xafiI0s",
  authDomain: "notification-b609d.firebaseapp.com",
  databaseURL: "https://notification-b609d.firebaseio.com",
  projectId: "notification-b609d",
  storageBucket: "notification-b609d.appspot.com",
  messagingSenderId: "759224230234"
};
firebase.initializeApp(config);
const messaging = firebase.messaging();
