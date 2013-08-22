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

```scala
import com.esyfur.{rql => r}

val host = "localhost"
val db   = "awesomeness"
val tbl  = "marvel"

val conn = r.connect(host).repl().use(db)
r.db(db).table(tbl).limit(5).run

r.dbList.run
r.db(db).tableList.run(conn)
conn.db.tableList.limit(1).run
r.db(db).table(tbl).slice(1, 2).run()
r.db(db).table(tbl).isEmpty.run()

```

## Development

To re-download and re-compile the protobuf API:
```bash
gradle protoApiDownload protoApiCompile
```

To run the test suite:
```bash
gradle test
```
