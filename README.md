# BData
[![GitHub release](https://img.shields.io/github/v/release/lucamazzza/BData?color=green&label=latest%20release&sort=semver)](https://github.com/lucamazzza/BData/releases/latest)
[![JDK Version](https://img.shields.io/badge/JDK-17-aa8664.svg)](https://www.oracle.com/java/technologies/downloads/#java17)
[![License](https://img.shields.io/badge/License-MIT-purple)](LICENSE)
[![Discord Badge](https://img.shields.io/discord/1119987238202261664?color=5865F2&label=&logo=discord&logoColor=white)](https://discord.gg/B3yXwmHb2V)

BData is a library to manage data using better, dynamic, weakly-typed data types.
It is written in Java, based on Java's standard Arrays, for high compatibility.

> ⚠️ BData is still in development and is not available yet! Be patient or join my [Discord Community](https://discord.gg/B3yXwmHb2V) to interact with me and contribute.

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
List<Data> datas = new ArrayList<Data>();
datas.add(table1); // Works
datas.add(tuple2); // Works
```

> Many more features are to come, be patient. If you have an enquiry, an idea, or you just want to help me out please submit via issue. 

## Requirements


## Installation


## Usage


## Credits
BData is written and mantained by [Luca Mazza](https://mazluc.ch)

## License
BData is released under the MIT License.
See [License](LICENSE) for details.
