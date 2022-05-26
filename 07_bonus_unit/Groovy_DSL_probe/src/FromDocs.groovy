class FromDocs {
    static void main(String[] args) {
        def map = [
                Bob  : 42,
                Alice: 54,
                Max  : 33
        ]

// `entry` is a map entry
        map.each { entry ->
            println "Name: $entry.key Age: $entry.value"
        }

// `entry` is a map entry, `i` the index in the map
        map.eachWithIndex { entry, i ->
            println "$i - Name: $entry.key Age: $entry.value"
        }

// Alternatively you can use key and value directly
        map.each { key, value ->
            println key
            println value
        }

// Key, value and i as the index in the map
        map.eachWithIndex { key, value, i ->
            println "$i - Name: $key Age: $value"
        }
    }
}
