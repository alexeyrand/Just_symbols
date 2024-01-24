## **Just symbols**  
### **Описание проекта**  
Приложения для подсчета символов в строке
### **Структурная схема архитектуры** 
Message flow:
![Structure schema](/images/schema.png)
### **Технологии**
+ Java 17
+ Spring Boot 3
+ REST
### **Документация API**
1) **Endpoint "/api/symbols"**

- **Request**

| Request param | Format                       | Usage Cinditions | Descriptions                               |
| ------------- | ---------------------------  | ---------------- | ----------------                           |
| str           | String, максимум 30 символов |         M        | В качестве параметра принимается строка. Сервер вернет Json с колличеством символов заданной строки. Строка не должна быть пустой, между символами не должно быть пробелов, максимальная длинна строки - 30 симолов. |

- **Example**
GET: "http://localhost:8080/api/symbols?str=aaabbc"**

- **Response**

| Responses param | Format     | Descriptions                               |
| --------------- | ---------  | ----------------                           |
| Symbol          | Number     | Колличество соответствующего символа в переданной строке |

- **Example**  
{  
      "a": 3,  
      "b": 2,  
      "c": 1  
}
