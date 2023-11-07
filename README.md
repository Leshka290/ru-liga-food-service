## Food Service

## Сервис по доставке еды.
Многомодульное Spring-Boot приложение, в котором каждый модуль отвечает за свою часть функциональности.

## Функционал приложения: 
- Авторизация пользователя
- Формирование заказа
- Доставка
- Рассылка курьерам уведомлений
##  Модули приложения
## dependency_bom
- Модуль отвечает за общие зависимости сервисов. Содержит версии и настройки зависимостей.
## auth-service
- Модуль отвечает за авторизацию и аутентификацию.
## common
- Модуль в котором представлены все общие сущности и общие методы.
## notification-service
- Модуль отвечает за отправку уведомлений курьерам.
## migration
- Модуль в котором представлены все скрипты миграции.
## delivery-service
- Модуль отвечает за доставку заказа.
## kitchen-service
- Модуль отвечает приготовление блюд.
## order-service
- Модуль отвечает за обработку заказа.

## Используемые технологии
<div>
  <img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original-wordmark.svg" title="Java" alt="Java" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/spring/spring-original-wordmark.svg" title="Spring" alt="Spring" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/postgresql/postgresql-original.svg" title="PostgreSQL"  alt="PostgreSQL" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/git/git-original-wordmark.svg" title="Git" **alt="Git" width="40" height="40"/>
</div>
