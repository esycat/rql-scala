# RethinkDB Scala Driver

The driver implements only the base part of the [API](http://rethinkdb.com/api/).

It should work for RethinkDB v1.7.

## Installation

### SBT

```scala
appDependencies ++= Seq(
    "com.esyfur" % "rql" % "0.1"
)
```

### Gradle

```groovy
dependencies {
    compile group: 'com.esyfur', name: 'rql', version: '0.1'
}
```

### Maven

```xml
```


## Usage

## Development

To re-download and re-compile the protobuf API:
```bash
gradle protoApiDownload protoApiCompile
```

To run the test suite:
```bash
gradle test
```
