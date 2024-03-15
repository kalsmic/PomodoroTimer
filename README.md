# Pomodoro Timer APP

This app is built to boost your productivity as you make the best use of the time you have, to do the things that matter to you.

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:kalsmic/PomodoroTimer.git
```

## requirements
- minimum SDK Version 16
- target SDK Version 30

## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

## Documentation
 - Application Description on [Google slide](https://docs.google.com/presentation/d/1mjLYEEnoPKIB0fsILe6BPA5AWN4WKqMI4GAfFcpiuJE/edit?usp=sharing)
 - Mock Up designs on (Marvel](https://marvelapp.com/prototype/6705eaf)


## Release App
```bash
openssl x509 -inform DER -outform PEM -in deployment_cert.der -out certificate.pem
```

```bash
keytool -genkeypair -v -keystore pomodoro-timer-release-key.jks -keyalg RSA -keysize 2048 -validity 20000 -alias pomodoro_timer -file certificate.pem
```

```bash
keytool -certreq -v -alias pomodoro_timer -keystore pomodoro-timer-release-key.jks -file certificate.pem
```

```bash
keytool -list -v -keystore pomodoro-timer-release-key.jks
```
## Credits
[Hands Power Photo](https://stocksnap.io/photo/hands-power-XG0J32JHTM) by [Ben White](https://stocksnap.io/author/34619) on [Stock Snap](https://stocksnap.io)

## My Motivation
- If you use the time you have to do the things you love, you will achieve want you want in the time you have.
- Time is always enough only if you make the best use of it.


F3:84:D4:BD:51:26:8C:E8:C1:47:0D:9B:40:55:F9:9F:20:D3:B1:07
62:4B:C6:34:69:04:2B:ED:F3:2E:42:77:2C:52:40:89:90:59:2B:CB:B8:50:39:67:7A:91:AF:AC:90:FA:7E:6B