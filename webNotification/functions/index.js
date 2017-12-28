const functions = require('firebase-functions');
const admin=require('firebase-admin');
admin.initializeApp(functions.config().firebase);
db=admin.firestore();
// exports.date = functions.https.onRequest((req, res) => {
//   //console.log("function http called");
//   res.send("Date");
//   return 0;
// });

exports.onNotificationArrived=functions.firestore.document('{email}/{id}').onCreate(event=>{
  console.log("notification for  user id="+event.data.data());
   db.collection('/'+event.params.email).doc('token').get().then(doc=>{
      console.log("data exists ="+doc.data()["token"]);
      const payload =
      {
      notification:
      {
       title: event.data.data()["title"],
       body: event.data.data()["body"],
       sound:'default',
       icon:'https://airnativeextensions.com/images/extensions/icons/ane-firebase-icon.png'
      }
      };
      admin.messaging().sendToDevice(doc.data()["token"], payload)
      .then(function(response) {
        console.log("Successfully sent message:", response);
      })
      .catch(function(error) {
        console.log("Error sending message:", error);
      });

    }).catch(err=>{
      console.log("error acessing data");
    });
    return 0;
});
