# HMSPushDataMessage
HMS Push Kit - Data Message to Notification

## Server Side
(using Postman)

<hr>
<br>

## 1 - Create Token

### 1.1 - Using Version 2

Url: 
https://login.cloud.huawei.com/oauth2/v2/token

Body:
```
grant_type:client_credentials
client_id:CLIENT_ID
client_secret:CLIENT_SECRET
```

<br><br>

<img src="https://www.yuklio.com/f/qk7dg-create_token_v2.png">

------------------------------------------


### 1.2 - Using Version 3

Url: 
https://oauth-login.cloud.huawei.com/oauth2/v3/token

Body:
```
grant_type:client_credentials
client_id:CLIENT_ID
client_secret:CLIENT_SECRET
```

<br><br>

<img src="https://www.yuklio.com/f/KaUCn-create_token_v3.png">

------------------------------------------

## Send Data Message

Url:
https://push-api.cloud.huawei.com/v1/APP_ID/messages:send

Authorization:
```
Bearer TOKEN
```

<br><br>

<img src="https://www.yuklio.com/f/mNQMe-send_data_auth_2.png">

<br><br>


## Variables

<table>
        <tr>
            <td>
                Variable
            </td>
            <td>
                Description
            </td>
            <td>
                Require
            </td>
        </tr>
    <tr>
        <td>
            type
        </td>
        <td>
            "notification" or null for "data"
        </td>
        <td></td>
    </tr>
    <tr>
        <td>
            title
        </td>
        <td>
            String 
        </td>
        <td>
            *
        </td>
    </tr>
    <tr>
        <td>
            content
        </td>
        <td>
            String 
        </td>
        <td>
            *
        </td>
    </tr>
    <tr>
        <td>
            small_icon
        </td>
        <td>
            if is null it's mean R.mipmap.ic_launcer. If not, you can select any image from R.drawable.____  
        </td>
        <td></td>
    </tr>
    <tr>
        <td>
            style
        </td>
        <td>
            "big_text" for Arama Sonuçları Notification.BigTextStyle or you can set null for normal style
        </td>
        <td></td>
    </tr>
    <tr>
        <td>
            big_text
        </td>
        <td>
            String
        </td>
        <td>
            If you set style of "big_text"
        </td>
    </tr>
    <tr>
        <td>
            on_click
        </td>
        <td>
            Default is "open_app" or you can write custom value 
        </td>
        <td></td>
    </tr>
    <tr>
        <td>
            data
        </td>
        <td>
            Be careful about this variable's type. It is must be String. For example; {\"key\":\"value\",\"key2\":\"value2\"}
        </td>
        <td></td>
    </tr>
</table>

<br>

Body (Sample):

```json
{
    "validate_only": false,
    "message": {
        "data": "{\"type\": \"notification\", \"style\": \"big_text\", \"title\": \"NOTIFICATION TITLE\", \"content\": \"CONTENT\", \"big_text\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\", \"small_icon\": \"custom_icon\",  \"on_click\": \"open_app\", \"data\": {\"key\":\"value\",\"key2\":\"value2\"} }",
        "token": [
            "DEVICE_TOKEN"
        ]
    }
}
```

<img src="https://www.yuklio.com/f/exEWE-send_data_message_2.png">
