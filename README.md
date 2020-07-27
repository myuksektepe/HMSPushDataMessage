# HMSPushDataMessage
HMS Push Kit - Data Message to Notification

## Server Side
(using Postman)


## 1 - Create Token

### 1.1 - Using Version 2

Url: 
https://login.cloud.huawei.com/oauth2/v2/token

Body:
grant_type:client_credentials
client_id:CLIENT_ID
client_secret:CLIENT_SECRET

<img src="https://www.yuklio.com/f/qk7dg-create_token_v2.png">

------------------------------------------


### 1.2 - Using Version 3

Url: 
https://oauth-login.cloud.huawei.com/oauth2/v3/token

Body:
grant_type:client_credentials
client_id:CLIENT_ID
client_secret:CLIENT_SECRET

<img src="https://www.yuklio.com/f/KaUCn-create_token_v3.png">

------------------------------------------

## Send Data Message

Url:
https://push-api.cloud.huawei.com/v1/APP_ID/messages:send

Authorication:
Bearer TOKEN

<img src="https://www.yuklio.com/f/mNQMe-send_data_auth_2.png">


Body (Sample):
{
    "validate_only": false,
    "message": {
        "data": "{\"type\": \"notification\", \"style\": \"big_text\", \"title\": \"NOTIFICATION TITLE\", \"content\": \"CONTENT\", \"big_text\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\", \"small_icon\": \"custom_icon\",  \"on_click\": \"open_app\", \"data\": {\"key\":\"value\",\"key2\":\"value2\"} }",
        "token": [
            "DEVICE_TOKEN"
        ]
    }
}


<img src="https://www.yuklio.com/f/6diTM-send_data_message.png">



## Variables

{
    "type": "notification", // or "data" (Require)
    "title": "TITLE", (Require)
    "content": "CONTENT TEXT", (Require)

    "small_icon": "", // if is null it's mean R.mipmap.ic_launcer, if not you can select any image from R.drawable.____ 
    "style": "", // if is null it's mean normal or you can write "big_text"
    "big_text": "", // if "style" is "big_text" this field must be full
    
    "on_click": "", //if is null it's mean "open_app"

    "data": {\"key\":\"value\",\"key2\":\"value2\"}
}


