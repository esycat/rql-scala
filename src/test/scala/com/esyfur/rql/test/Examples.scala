package com.esyfur.rql.test

import com.esyfur.{rql => r}


class Examples {

    var conn: r.Connection = _

    var host = "localhost"
    var port = 28015
    var authKey = "hunter2"
    var dbName = "superheroes"
    var tblName = "marvel"

    def callback = ??? // function (err, cursor) { ... }

    def connManipulation {
        conn = r.connect(host = host, port = port, db = dbName)
        //conn = r.connect(host = host, port = port, db = dbName, authKey = authKey, callback = callback)
        conn.close()
        conn.reconnect(/*callback*/)
        conn.use(dbName)
    }

    /*
    def someUsage {
        r.table(tblName).run(conn, function(err, cur) { cur.each(console.log); })
        r.table(tblName).run({connection:conn, useOutdated:true}, callback)
        r.table(tblName).run({connection:conn, noreply:true}, callback)
        r.table(tblName).insert({ superhero: "Iron Man", superpower: "Arc Reactor" }).run({connection:conn, noreply:true, durability: "soft"}, callback)

        cur.next(function(err, row) { return processRow(row); })
        var hasMore = cur.hasNext()
        cur.each(function(err, row) { processRow(row); })

        cur.each(function(err, row) { return processRow(row) }, function() { doneProcessing() })

        cur.toArray(function(err, results) { for(var i in results) { processRow(results[i]); }})

        r.connect({}, function(err, conn) {
            if (err) throw err;

            conn.addListener("error", function(e) {
                processNetworkError(e);
            });

            conn.addListener("close", function() {
                cleanup();
            });

            runQueries(conn);
        });

        conn.on("close", function() {
            cleanup();
        });

        conn.close();
    }
    */

    def dbManipulation {
        r.dbCreate(dbName).run(conn /*, callback*/)

        r.dbDrop(dbName).run(conn /*, callback*/)

        r.dbList.run(conn /*, callback*/)
    }

    def tableManipulation {
        r.db(dbName).tableCreate("dc_universe").run(conn /*, callback*/)

        r.db(dbName).tableCreate("dc_universe" /*, {primaryKey: "name"}*/).run(conn /*, callback*/)

        r.db(dbName).tableCreate("dc_universe" /*, {hardDurability: false}*/).run(conn /*, callback*/)

        r.db(dbName).tableDrop("dc_universe").run(conn /*, callback*/)

        r.db(dbName).tableList.run(conn /*, callback*/)
    }

    def indexManipulation {
        r.table(tblName).indexCreate("code_name").run(conn /*, callback*/)

        /*
        r.table(tblName).indexCreate("power_rating", function(hero) {
            return hero("combat_power").add(hero("compassion_power").mul(2))
        }).run(conn, callback)
        */

        /*
        r.table("dc").indexCreate("parental_planets", function(hero) {
            return [hero("mothers_home_planet"), hero("fathers_home_planet")]
        }).run(conn, callback)
        */

        r.table(tblName).indexDrop("code_name").run(conn /*, callback*/)

        r.table(tblName).indexList().run(conn /*, callback*/)
    }

    def writingData {
        //r.table(tblName).insert({ superhero: "Iron Man", superpower: "Arc Reactor" }).run(conn /*, callback*/)

        /*
        r.table(tblName).insert([
            { superhero: "Wolverine", superpower: "Adamantium" },
            { superhero: "Spiderman", superpower: "spidy sense" }
            ], { durability: "soft" }).run(conn, callback)
        */

        /*
        r.table(tblName).insert(
        { superhero: "Iron Man", superpower: "Arc Reactor" },
        {upsert: true}
        ).run(conn, callback)
        */

    }

}
