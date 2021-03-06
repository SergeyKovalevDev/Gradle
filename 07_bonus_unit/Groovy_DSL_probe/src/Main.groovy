class Main {
    static void main(String[] args) {

        // Основы Groovy
        // Groovy является надмножеством Java.
        // Это означает, что большая часть кода Java является допустимым кодом Groovy.
        // Проиллюстрируем это на небольшом примере.

        System.out.println("Hello World");

        // Это допустимый код Groovy.
        // В Groovy вы можете просто поместить это в файл, выполнить его через консоль, и он будет работать.
        // Можно сократить эту строку до:

        println "Hello world!"

        // Переменные
        // В отличие от Java, в Groovy можно использовать динамическую типизацию.
        // Для определения переменной используется ключевое слово def.
        // Таким образом Groovy определит тип переменной во время выполнения, и этот тип может даже измениться.
        // Например, следующий фрагмент является допустимым кодом Groovy:

        def x = 42
        println x.getClass()
        x = "Hello World"
        println x.getClass()

        // Этот сценарий производит вывод:

        //  class java.lang.Integer
        //  class java.lang.String
        // Переменная x изменила свой тип во время выполнения.
        // Можете определять x с типом int. В этом случае второе присвоение вызовет ошибку,
        // потому что вы явно указали Groovy, что хотите, чтобы эта переменная имела тип int,
        // и она не может неявно преобразовать String в Integer.

        // Строки
        // Groovy имеет реализацию String под названием GString, которая позволяет добавлять переменные в String (интерполяция строк).

        def y = "World"
        println "Hello, $y"

        // Это приведет к выводу Hello, World. В строку вставляется содержимое переменной x.
        // Если вы хотите использовать более сложные операторы в строке, вам нужно добавить
        // фигурные скобки после знака доллара, например:

        def letters = "ABC"
        println "Первая буква: ${letters[0]}"

        // Это приведёт к выводу A.
        // Вместо использования двойных кавычек (”) вы также можете использовать одинарные кавычки (') в качестве разделителей строк.
        // Одинарные кавычки создают привычную Java строку без каких-либо функций интерполяции,
        // тогда как двойные кавычки при необходимости создают GString, чтобы интерполяция работала.
        // Поэтому вам нужно будет экранировать знак доллара в строке с двойными кавычками,
        // если вы хотите распечатать сам знак доллара.
        // Groovy также поддерживает многострочные строки.
        // Просто используйте три двойные или одинарные кавычки (в зависимости от типа), чтобы создать многострочную строку:

        def s = """Это
        строка
        из нескольких строк"""

        // Преобразование к логическому значению
        // Как и в JavaScript, Groovy при необходимости может расценивать каждый объект как логическое значение
        // (например, при использовании его внутри условия или при отрицании значения).
        // Groovy использует следующие правила для преобразования любого типа в логический:
        // строки: пустая строка — false, иначе — true;
        // коллекции и словари: true, если они не являются пустыми;
        // все ненулевые числа: true;
        // matchers (из проверки регулярного выражения): true, если нашлось хотя бы одно совпадение;
        // iterators с другими элементами: true;
        // ссылки на объекты: true, если не null (можно определить собственную логику, реализовав метод boolean asBoolean()).

        // Оператор безопасной навигации
        // Прежде чем обращаться к свойствам объекта, нужно убедиться в существовании объекта:

//        if (company.getContact() != null
//                && company.getContact().getAddress() != null
//                && company.getContact.getAddress().getCountry() == Country.RUSSIA {
//            // какой-то код
//        }

        // Groovy предотвращает это с помощью оператора безопасной навигации — ?.
        // Если в цепочке какой-то объект равен null, то в результате null часть будет равна нулю.
        // Вместо этого весь результат навигации будет нулевым:
        // if (company.getContact()?.getAddress()?.getCountry() == Country.RUSSIA) { ... }
        // Если контакт или адрес равны null, то результат в левой части будет null, но исключения не возникнет.

        // Оператор Элвис
        // Для проверки на наличие объекта код, приведённый ниже,

        // def name = client.getName() != null ? client.getName() : ""

        //можно заменить на присваивание значения по умолчанию:

        //def name = client.getName() ?: ""

        //Название этот оператор получил в честь Элвиса Пресли: посмотрите на ?:, наклонив голову влево — похоже? :)

        // Списки
        // Создание и инициализация списков намного проще:

        def list = [0, 1, 2, 3, 5]

        // Эта конструкция создаст ArrayList с указанными элементами.
        // Обращение к элементам похоже на обращение к элементам массива:

        println list[0]
        println list[-1]

        // Преобразование и фильтрация
        // Чтобы перебрать список, вы можете использовать каждый each:

        list.each {
            print it
        }

        println()

        // Замыкание выполняется однократно для каждого элемента.
        // Элемент списка будет передан в качестве параметра замыканию (неявно при помощи it).
        // Для фильтрации списка используется метод findAll:

        def even = list.findAll {
            it % 2 == 0
        }
        even.each { print it }

        println()

        // Этот метод создает новый список, вызывая переданное замыкание для каждого элемента
        // в исходном списке и добавляя его в новый список, если замыкание возвращает значение, которое оценивается как true.
        // Если вас интересует только первый элемент списка, который соответствует заданным критериям, используйте метод find.
        // Он также принимает замыкание и возвращает первый элемент, для которого замыкание оценивается как true.

        // Для преобразования элементов списка используйте метод collect:

        def squaredList = list.collect {
            it * it
        }
        squaredList.each { print(it) }

        // Он создаст новый список путём преобразования каждого элемента в исходном списке заданным замыканием.
        //Если единственное преобразование, которое вы хотите выполнить в замыкании — это вызов метода для каждого элемента,

        def upper = ["Hello", "World"].collect { it.toUpperCase() }

        // то можно использовать spread-dot оператор:

        def upper1 = ["Hello", "World"]*.toUpperCase()

        // Работа с Map
        // Создание Map также имеет упрощенный синтаксис:

        def key = 'Key3'
        def aMap = [
                'Key1': 'Value 1', // Помещает key1 -> Value 1 в мапу
                Key2: 'Value 2', // Кавычки для ключа можно опустить, он будет преобразован в строку
                (key): 'Other value' // Заключив ключ в круглые скобки мы можем использовать переменную
        ]

        // В результате получим LinkedHashMap с записями:
        // Key1 -> Value1,
        // Key2 -> Value 2,
        // Key3 -> Other Value.

        // Для создания пустой Map:

        def map = [:]

        // Для доступа к записям можно использовать квадратные скобки

        println aMap['Key1'] // Доступ по ключу Key1
        println aMap[key] // Использование переменной-ключа

        // или точечную нотацию:

        println aMap.Key1 // Доступ по ключу Key1

        // Если ключ имеет пробел:

        aMap.'Key With Spaces'

        // Вы даже можете использовать интерполированные строки:

        aMap."$key" // совпадает с aMap[key]

        // Преобразование и фильтрация
        // Для фильтрации и преобразования есть те же методы, что и для списков:

        println "------"
        map.put("K1", "V1")
        map.put("K2", "V2")
        map.put("K3", "V3")
        map.each {
            print it.key
            print " - "
            println it.value
        }

        // Каждый метод также может принимать замыкание с двумя параметрами (ключ и значение):

        map.each { keyB, value ->
            print keyB
            print " - "
            println value
        }

        // То же самое правило применяется для методов find, findAll, any и every.
        // Вы можете либо принять один аргумент в замыкании, и в этом случае вы получите запись,
        // либо можете напрямую принять ключ и значение. any и every вернут логическое значение,
        // findAll создаст новую карту, содержащую только записи, для которых было оценено
        // замыкание true, и метод find вернёт первую запись (а не только значение), для которой было оценено замыкание true.

        // Единственный метод, который ведёт себя совершенно иначе — метод collect.
        // Метод collect создаст список в качестве вывода. Он будет перебирать все записи на карте и передавать их
        // (как один или два параметра) в закрытие. Все объекты, которые вы возвращаете из закрытия, будут добавлены в список (а не на карту).
        // Таким образом, следующее работает не так, как можно было бы ожидать:

        map.collect { return it }

        // Вы получите список записей, что, скорее всего, не то, что вам нужно.
        // Для преобразования Map в Map вы должны использовать метод collectEntries:

        map.collectEntries {
            return it
        }

        // Вы можете вернуть запись в замыкании, которая будет помещена в новую Map (в приведённом выше примере снова будет создана та же самая Map).

        // Если вы хотите создать новые записи, вы можете вернуть карту (чаще всего с одной записью) из замыкания:

        map.collectEntries { keyC, value ->
            return [(keyC): value]
        }

        // Этот фрагмент делает то же, что и приведённый выше. Он просто добавит каждую запись в новую карту.
        // Вы даже можете вернуть Map, содержащую более чем одну запись.
        // В этом случае все записи на карте будут перенесены на новую карту:

        map.collectEntries { keyC, value ->
            def newKey = keyC + "^2"
            return [(keyC): value, (newKey): value*value]
        }

        // Этот фрагмент добавит каждую запись из исходной Map и новую с добавлением ^ 2 к исходному ключу
        // и значением, возведённым в квадрат, в новую Map.



    }
}
