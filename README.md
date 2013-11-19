# RethinkDB Scala Driver

The driver implements only the base part of the [API](http://rethinkdb.com/api/).

It should work for RethinkDB v1.8.

## Installation

### SBT

```scala
appDependencies ++= Seq(
    "com.esyfur" % "rql" % "0.1.+"
)
```

### Gradle

```groovy
dependencies {
    compile group: 'com.esyfur', name: 'rql', version: '0.1.+'
}
```

### Maven

```xml
<dependency>
  <groupId>com.esyfur</groupId>
  <artifactId>rql</artifactId>
  <version>0.1.+</version>
</dependency>
```


## Usage

See the `Main` class for usage examples.
Try `gradle run` to execute the class.

```scala
import com.esyfur.{rql => r}

val host = "localhost"
val db   = "awesomeness"
val tbl  = "beautifulThings"

val data = Map(...)

// connect to the server, set default connection and database
val conn = r.connect(host).repl().use(db)

// create a new database and show a list of existing databases
r.dbCreate(db).run()
r.dbList.run()

// create a new table and show a list of existing tables in the database
r.db(db).tableCreate(tbl).run()
r.db(db).tableList.run()

// insert some data
r.db(db).table(tbl).insert(data).run()

// select data from the table in different ways
r.db(db).table(tbl).limit(5).run()
r.db(db).table(tbl).slice(1, 2).run()

// check whether the table is empty
r.db(db).table(tbl).isEmpty.run()
r.db(db).table(tbl).count().run()

// math expressions
r.expr(5).add(2).add(10).sub(12).mul(6).div(3).mod(3).run()

// cleanup and disconnect
r.db(db).tableDrop(tbl).run()
r.dbDrop(db).run()
conn.close()
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
