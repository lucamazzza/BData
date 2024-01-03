# BData
[![GitHub release](https://img.shields.io/github/v/release/lucamazzza/BData?color=green&label=latest%20release&sort=semver)](https://github.com/lucamazzza/BData/releases/latest)
[![JDK Version](https://img.shields.io/badge/JDK-17-aa8664.svg?logo=oracle)](https://www.oracle.com/java/technologies/downloads/#java17)
[![License](https://img.shields.io/badge/License-MIT-purple)](LICENSE)
[![Discord Badge](https://img.shields.io/discord/1119987238202261664?color=5865F2&label=&logo=discord&logoColor=white)](https://discord.gg/B3yXwmHb2V)

BData is a library to manage data using better, dynamic, weakly-typed data types.
It is written in Java, based on Java's standard Arrays, for high compatibility.

## Features
Currently the features of BData are the following:
* **`Tuple`**: a structured, heterogeneous data type, representing a Tuple (as seen in Python, Swift and many others), being an "array with multiple types inside".
``` java
Tuple tuple1 = new Tuple(); // An empty tuple
Tuple tuple2 = new Tuple("Hi", "i", 0, false, 0.0f, 554.23);
```

* **`Table`**: representing an aggregation of Tuples.
``` java
Table table1 = new Table(); // An empty table
Table table2 = new Table(tuple1, tuple2);
```

* **`Data`**: an interface representing Tuples and Tables.
``` java
Data table3 = new Table(tuple1, tuple2);
Data tuple4 = new Tuple(200, "OK");
List<Data> datas = new ArrayList<Data>();
datas.add(table3); // Works
datas.add(tuple4); // Works
```

> If you have an enquiry, an idea, or you just want to help me out please submit via issue. 

## Requirements
**`BData`** is written using the JDK 17, and is supported on this version or later. In order to use this library you'll need a complete Java Environment, with JDK, JVM and optionally other development packages, on version 17 or older. 
[Download JDK](https://www.oracle.com/java/technologies/downloads/#java17)

## Installation
First of all download the `.jar` file from the releases section; you can determine which release suits you the best looking at release notes.

Follow then the procedure of the following IDEs:

### IntelliJ Idea
Go to your project structure settings (`⌘è` on macOS) and select the modules item.
Select then `dependencies` and add a new one by selecting the option 'JAR or Directories'.
![image](https://github.com/lucamazzza/BData/assets/36283355/a5d1a809-0605-4a88-bafc-dff2379c8741)
Click then on apply and you are good to go.

### Eclipse
Right-click on the Project → Build Path → Configure Build Path.
Under the libraries tab, click 'Add JARs' and select the JAR-File you downloaded.
![image](https://github.com/lucamazzza/BData/assets/36283355/af31d242-3cc0-42ef-9775-fb5700cf14ba)
Click then 'Apply and Close' and you are good to go.

## Usage


## Credits
BData is written and mantained by [Luca Mazza](https://mazluc.ch)

## License
BData is released under the MIT License.
See [License](LICENSE) for details.
