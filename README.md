# GoldSrcRcon
Simple library for the connect to GoldSrc (HL &amp; CS1.6) Rcon. 
- Only tested with counter-strike.

## Usage

```java
GoldSrcRcon rcon = new GoldSrcRcon("213.238.173.37", 27015, "rcon_password");
String result = rcon.sendConsoleCommand("stats");
System.out.println(result);
```

Response:
```
CPU   In    Out   Uptime  Users   FPS    Players
13.00 44.22 64.93    8837  6327  298.93       8
```

## Download
[Get](https://github.com/ahm3tcelik/GoldSrcRcon/releases/latest) the latest packed .jar.
