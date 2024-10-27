# Шаблон Java-проекта для домашних заданий

Шаблон для домашних заданий [Академии Бэкенда 2024][course-url].

Цель данного репозитория – познакомить вас с процессом разработки приложений на
Java с использованием наиболее распространенных практик, инструментов и
библиотек.

## Структура проекта

Это типовой Java-проект, который собирается с помощью инструмента автоматической
сборки проектов [Apache Maven](https://maven.apache.org/).

Проект состоит из следующих директорий и файлов:

- [pom.xml](./pom.xml) – дескриптор сборки, используемый maven, или Project
  Object Model. В нем описаны зависимости проекта и шаги по его сборке
- [src/](./src) – директория, которая содержит исходный код приложения и его
  тесты:
  - [src/main/](./src/main) – здесь находится код вашего приложения
  - [src/test/](./src/test) – здесь находятся тесты вашего приложения
- [mvnw](./mvnw) и [mvnw.cmd](./mvnw.cmd) – скрипты maven wrapper для Unix и
  Windows, которые позволяют запускать команды maven без локальной установки
- [checkstyle.xml](checkstyle.xml),
  [checkstyle-suppression.xml](checkstyle-suppression.xml), [pmd.xml](pmd.xml) и
  [spotbugs-excludes.xml](spotbugs-excludes.xml) – в проекте используются
  [линтеры](https://en.wikipedia.org/wiki/Lint_%28software%29) для контроля
  качества кода. Указанные файлы содержат правила для используемых линтеров
- [.mvn/](./.mvn) – служебная директория maven, содержащая конфигурационные
  параметры сборщика
- [lombok.config](lombok.config) – конфигурационный файл
  [Lombok](https://projectlombok.org/), библиотеки помогающей избежать рутинного
  написания шаблонного кода
- [.editorconfig](.editorconfig) – файл с описанием настроек форматирования кода
- [.github/workflows/build.yml](.github/workflows/build.yml) – файл с описанием
  шагов сборки проекта в среде Github
- [.gitattributes](.gitattributes), [.gitignore](.gitignore) – служебные файлы
  для git, с описанием того, как обрабатывать различные файлы, и какие из них
  игнорировать

## Начало работы

Подробнее о том, как приступить к разработке, описано в разделах
[курса][course-url] `1.8 Настройка IDE`, `1.9 Работа с Git` и
`1.10 Настройка SSH`.

Для того чтобы собрать проект, и проверить, что все работает корректно, можно
запустить из модального окна IDEA
[Run Anything](https://www.jetbrains.com/help/idea/running-anything.html)
команду:

```shell
mvn clean verify
```

Альтернативно можно в терминале из корня проекта выполнить следующие команды.

Для Unix (Linux, macOS, Cygwin, WSL):

```shell
./mvnw clean verify
```

Для Windows:

```shell
mvnw.cmd clean verify
```

Для окончания сборки потребуется подождать какое-то время, пока maven скачает
все необходимые зависимости, скомпилирует проект и прогонит базовый набор
тестов.

Если вы в процессе сборки получили ошибку:

```shell
Rule 0: org.apache.maven.enforcer.rules.version.RequireJavaVersion failed with message:
JDK version must be at least 22
```

Значит, версия вашего JDK ниже 22.

Если же получили ошибку:

```shell
Rule 1: org.apache.maven.enforcer.rules.version.RequireMavenVersion failed with message:
Maven version should, at least, be 3.8.8
```

Значит, у вас используется версия maven ниже 3.8.8. Такого не должно произойти,
если вы запускаете сборку из IDEA или через `mvnw`-скрипты.

Далее будут перечислены другие полезные команды maven.

Запуск только компиляции основных классов:

```shell
mvn compile
```

Запуск тестов:

```shell
mvn test
```

Запуск линтеров:

```shell
mvn checkstyle:check modernizer:modernizer spotbugs:check pmd:check pmd:cpd-check
```

Вывод дерева зависимостей проекта (полезно при отладке транзитивных
зависимостей):

```shell
mvn dependency:tree
```

Вывод вспомогательной информации о любом плагине (вместо `compiler` можно
подставить интересующий вас плагин):

```shell
mvn help:describe -Dplugin=compiler
```

## Дополнительные материалы

- Документация по maven: https://maven.apache.org/guides/index.html
- Поиск зависимостей и их версий: https://central.sonatype.com/search
- Документация по процессу автоматизированной сборки в среде github:
  https://docs.github.com/en/actions
- Документация по git: https://git-scm.com/doc
- Javadoc для Java 22:
  https://docs.oracle.com/en/java/javase/22/docs/api/index.html

[course-url]: https://edu.tinkoff.ru/all-activities/courses/870efa9d-7067-4713-97ae-7db256b73eab


# Документация к игре "Лабиринты"

## Описание проекта

Проект представляет собой игру с лабиринтом, в которой реализованы алгоритмы генерации и поиска оптимального пути. Приложение предоставляет две версии игры с различной сложностью и выбором стратегий поиска пути.
## Версии игры
#### 1. Версия 1 — Генерация лабиринта и поиск пути из точки А в точку В

В этой версии приложение автоматически генерирует лабиринт и строит маршрут между двумя заданными точками — **А** (старт) и **В** (финиш). Пользователь может наблюдать процесс генерации лабиринта и поиска пути с помощью одного из алгоритмов, реализованных в приложении.

#### 2. Версия 2 — Лабиринт с дополнительными поверхностями и выбором нескольких точек входа

Эта версия добавляет сложность в виде нескольких точек входа в лабиринт. Пользователю предлагается выбрать одну из доступных точек входа и попробовать найти оптимальный маршрут до точки выхода. Если выбранный маршрут окажется не оптимальным (с более высокой стоимостью), программа подскажет пользователю более благоприятную точку входа, которая обеспечивает минимальную стоимость пути.

## Запуск приложения
Запуск приложения происходит в классе `Main`. Чтобы запустить игру, исполните данный класс. Логика игры поделена на два класса: GameVersion1 и GameVersion2. Оба класса содержутся в пакете game.
### Версии Игры

- **Версия 1**: 
  - Логика игры для первой версии реализована в методе `gameVersion1()`.
  
- **Версия 2**:
  - Для второй версии логика игры реализована в методе `gameVersion2()`. 


## Классы приложения
### Генераторы 
В этом разделе описаны алгоритмы генерации лабиринтов, используемые в приложении, а именно: Прима и Краскала.
## Алгоритмы генерации лабиринтов
#### Генератор Прима

Класс `MazeGeneratorBasedOnPrim` реализует  лабиринта с использованием алгоритма Прима.

**Методы:**

- **`generateMaze(int height, int width)`**:
  - **Описание**: Этот метод создает лабиринт заданного размера. Лабиринт будет состоять из ячеек, которые могут быть проходами или стенами. Метод генерирует лабиринт, начиная с произвольной клетки и постепенно добавляя новые проходы, основываясь на алгоритме Прима.
  - **Параметры**:
    - `height`: высота лабиринта.
    - `width`: ширина лабиринта.
  - **Возвращает**: Экземпляр класса `Maze`, представляющий сгенерированный лабиринт.

- **`findVisitedNeighbor(Cell cell, ArrayList<Coordinate> directions, Cell[][] grid, HashSet<Cell> visited)`**: 
  - **Описание**: Находит посещённую клетку, которая находится на 2 клетки дальше от заданной.
  - **Параметры**:
    - `cell`: клетка, для которой ищется сосед.
    - `directions`: список направлений для проверки.
    - `grid`: массив клеток лабиринта.
    - `visited`: множество посещённых клеток.
  - **Возвращает**: Посещённую клетку или `null`, если такой клетки нет.

- **`addNeighbor(Cell cell, ArrayList<Coordinate> directions, HashSet<Cell> neighbours, Cell[][] grid, HashSet<Cell> visited)`**: 
  - **Описание**: Добавляет непосещённые соседние клетки в множество соседей.
  - **Параметры**:
    - `cell`: клетка, для которой ищутся соседи.
    - `directions`: список направлений для проверки.
    - `neighbours`: множество соседних клеток.
    - `grid`: массив клеток лабиринта.
    - `visited`: множество посещённых клеток.

**Принцип работы:**

1. **Инициализация**: Генератор создает начальную клетку, выбирает произвольную стартовую точку и добавляет её в посещённые клетки.
2. **Добавление соседей**: Находятся соседи начальной клетки и добавляются в множество соседей.
3. **Цикл обработки**: Пока есть соседи, случайным образом выбирается сосед, и проверяется, есть ли у него посещённая клетка. Если такая клетка найдена, создается проход между клетками и соседи обновляются.

#### Генератор Краскала

Класс `MazeGeneratorBasedOnKruskal` генерирует лабиринт на основе алгоритма Краскала.

**Методы:**

- **`generateMaze(int height, int width)`**:
  - **Описание**: Создает лабиринт заданного размера с использованием алгоритма Краскала. Метод генерирует лабиринт, начиная с набора клеток и постепенно объединяя их, основываясь на ребрах, которые соединяют клетки.
  - **Параметры**:
    - `height`: высота лабиринта.
    - `width`: ширина лабиринта.
  - **Возвращает**: Экземпляр класса `Maze`, представляющий сгенерированный лабиринт.

- **`unionCell(ArrayList<Edge> edges, UnionFindImpl unionFind, Maze maze)`**: 
  - **Описание**: Объединяет клетки в остовное дерево. Метод проходит по всем ребрам, проверяет, находятся ли связанные клетки в одном множестве, и, если нет, объединяет их, создавая проход между ними.
  - **Параметры**:
    - `edges`: список рёбер, представляющих связи между клетками.
    - `unionFind`: экземпляр класса, реализующего структуру данных "Система непересекающихся множеств" (Union-Find).
    - `maze`: лабиринт, в котором происходит объединение клеток.
  - **Возвращает**: Список рёбер, представляющих остовное дерево (minimum spanning tree).

**Принцип работы:**

1. **Инициализация**: Генератор создает массив клеток, в котором каждое ребро представляет связь между двумя соседними клетками. Создается экземпляр `UnionFindImpl` для отслеживания соединений между клетками.
2. **Заполнение рёбер**: Метод `EdgeHandler.initSetEdges` используется для создания и заполнения рёбер на основе текущих клеток лабиринта.
3. **Сортировка рёбер**: Рёбра сортируются с помощью метода `EdgeHandler.sortEdges`.
4. **Объединение клеток**: Происходит проход по всем рёбрам, и для каждого ребра проверяется, находятся ли его клетки в одном множестве. Если нет, клетки объединяются, и создается проход между ними.

#### Интерфейс Генератора

Интерфейс `Generator` определяет контракт для всех классов, реализующих генерацию лабиринтов. Он включает методы для создания лабиринта, добавления проходов между клетками и проверки границ.

**Методы:**

- **`Maze generateMaze(int height, int width)`**: 
  - **Описание**: Метод для генерации лабиринта заданного размера.
  - **Параметры**:
    - `height`: высота лабиринта.
    - `width`: ширина лабиринта.
  - **Возвращает**: Экземпляр класса `Maze`, представляющий сгенерированный лабиринт.

- **`static void addPassageBetween(Coordinate current, Coordinate neighbor, Cell[][] grid)`**: 
  - **Описание**: Создает проход между двумя клетками в лабиринте. Этот метод вычисляет координаты клетки, находящейся между двумя другими, и устанавливает её как проход.
  - **Параметры**:
    - `current`: текущая клетка (объект класса `Coordinate`).
    - `neighbor`: соседняя клетка (объект класса `Coordinate`).
    - `grid`: двумерный массив клеток лабиринта.

- **`static int getNumberOdd(int parameter)`**: 
  - **Описание**: Проверяет, является ли переданный параметр нечётным, и при необходимости увеличивает его на 1, чтобы гарантировать, что возвращаемое значение всегда нечётное.
  - **Параметры**:
    - `parameter`: целое число для проверки.
  - **Возвращает**: Нечётное значение (если входное значение чётное, возвращается `parameter + 1`, иначе — само `parameter`).

## Алгоритмы для поиска путей в лабиринте 
Для поиска путей в лабиринтах я использовал алгоритмы: DFS, BFS, Dijkstra.
  ### Интерфейс Поиска Путей

Интерфейс `PathFinding` определяет контракт для всех классов, реализующих алгоритмы поиска пути в лабиринте. Он включает методы для нахождения пути между двумя точками, а также вспомогательные методы для получения соседей и восстановления пути.

**Методы:**

- **`ArrayList<Coordinate> findPath(Coordinate start, Coordinate end)`**: 
  - **Описание**: Метод для нахождения пути между заданными координатами `start` и `end`.
  - **Параметры**:
    - `start`: начальная точка (объект класса `Coordinate`).
    - `end`: конечная точка (объект класса `Coordinate`).
  - **Возвращает**: Список координат, представляющий найденный путь от `start` к `end`.

- **`static List<Coordinate> getNeighbors(Coordinate current, Maze maze)`**: 
  - **Описание**: Получает соседние клетки для текущей клетки `current`. Возвращает список соседних координат, которые являются проходимыми.
  - **Параметры**:
    - `current`: текущая клетка (объект класса `Coordinate`).
    - `maze`: экземпляр лабиринта (объект класса `Maze`).
  - **Возвращает**: Список соседей (объекты класса `Coordinate`), которые являются проходами.

- **`static void reconstructPath(Coordinate start, Coordinate end, Map<Coordinate, Coordinate> parentMap, ArrayList<Coordinate> path)`**: 
  - **Описание**: Восстанавливает путь от конечной точки `end` до начальной точки `start` с использованием карты родителей `parentMap`.
  - **Параметры**:
    - `start`: начальная точка (объект класса `Coordinate`).
    - `end`: конечная точка (объект класса `Coordinate`).
    - `parentMap`: карта, хранящая информацию о родителях для восстановления пути (объект типа `Map`).
    - `path`: список, в который будет добавлен восстановленный путь (объект типа `ArrayList<Coordinate>`).

### Класс Поиск в ширину (BreadthFirstSearch)

Класс `SearcherPathBasedOnBFS` реализует алгоритм поиска в ширину для нахождения пути в лабиринте. Он наследует от абстрактного класса `Solver` и реализует интерфейс `PathFinding`.

**Поля:**

- **`Queue<Coordinate> queue`**: 
  - **Описание**: Очередь для обработки клеток на текущем уровне. Используется для хранения клеток, которые необходимо исследовать.

**Конструкторы:**

- **`BreadthFirstSearch(Maze maze)`**: 
  - **Описание**: Конструктор, инициализирующий класс с заданным лабиринтом и создающий новую очередь для поиска.
  - **Параметры**:
    - `maze`: экземпляр класса `Maze`, представляющий лабиринт, который необходимо решить.

**Методы:**

- **`ArrayList<Coordinate> findPath(Coordinate start, Coordinate end)`**: 
  - **Описание**: Реализует алгоритм поиска в ширину для нахождения пути от стартовой до конечной клетки.
  - **Параметры**:
    - `start`: координаты стартовой клетки (объект класса `Coordinate`).
    - `end`: координаты конечной клетки (объект класса `Coordinate`).
  - **Возвращает**: Список координат клеток, составляющих путь от `start` до `end`, или `null`, если путь не найден.

  ### Класс Поиск в глубину (DepthFirstSearch)

Класс `SearcherPathBasedOnDFS` реализует алгоритм поиска в глубину для нахождения пути в лабиринте. Он наследует от абстрактного класса `Solver` и реализует интерфейс `PathFinding`.

**Поля:**

- **`Deque<Coordinate> dequeArray`**: 
  - **Описание**: Дек (двусторонняя очередь) для обработки клеток на текущем уровне. Используется для хранения клеток, которые необходимо исследовать, в порядке, характерном для поиска в глубину.

**Конструкторы:**

- **`DepthFirstSearch(Maze maze)`**: 
  - **Описание**: Конструктор, инициализирующий класс с заданным лабиринтом и создающий новую структуру данных для поиска.
  - **Параметры**:
    - `maze`: экземпляр класса `Maze`, представляющий лабиринт, который необходимо решить.

**Методы:**

- **`ArrayList<Coordinate> findPath(Coordinate start, Coordinate end)`**: 
  - **Описание**: Реализует алгоритм поиска в глубину для нахождения пути от стартовой до конечной клетки.
  - **Параметры**:
    - `start`: координаты стартовой клетки (объект класса `Coordinate`).
    - `end`: координаты конечной клетки (объект класса `Coordinate`).
  - **Возвращает**: Список координат клеток, составляющих путь от `start` до `end`, или `null`, если путь не найден.

  ### Класс Алгоритм Дейкстры (Dijkstra)

Класс `SearcherPathBasedOnDijkstra` реализует алгоритм Дейкстры для нахождения кратчайшего пути в лабиринте. Он наследует от абстрактного класса `Solver` и реализует интерфейс `PathFinding`.

**Поля:**

- **`List<Edge> mst`**: 
  - **Описание**: Список рёбер (границ), представляющий минимальное остовное дерево (MST) лабиринта.

- **`Queue<Coordinate> pqueue`**: 
  - **Описание**: Приоритетная очередь для обработки клеток в порядке возрастания расстояния.

- **`Set<Coordinate> nodes`**: 
  - **Описание**: Множество всех узлов (клеток), которые будут исследоваться.

- **`Map<Coordinate, Integer> distance`**: 
  - **Описание**: Словарь, хранящий расстояние от начальной клетки до каждой из клеток.

- **`int distanceSum`**: 
  - **Описание**: Сумма расстояний до конечной клетки.

- **`Maze maze`**: 
  - **Описание**: Экземпляр класса `Maze`, представляющий лабиринт, в котором осуществляется поиск пути.

**Конструкторы:**

- **`Dijkstra(ArrayList<Edge> mst, Maze maze)`**: 
  - **Описание**: Конструктор, инициализирующий класс с минимальным остовным деревом и заданным лабиринтом.
  - **Параметры**:
    - `mst`: список рёбер, представляющий минимальное остовное дерево.
    - `maze`: экземпляр класса `Maze`, представляющий лабиринт.

**Методы:**

- **`ArrayList<Coordinate> findPath(Coordinate start, Coordinate end)`**: 
  - **Описание**: Реализует алгоритм Дейкстры для нахождения кратчайшего пути от стартовой до конечной клетки.
  - **Параметры**:
    - `start`: координаты стартовой клетки (объект класса `Coordinate`).
    - `end`: координаты конечной клетки (объект класса `Coordinate`).
  - **Возвращает**: Список координат клеток, составляющих кратчайший путь от `start` до `end`, включая промежуточные точки, или пустой список, если путь не найден.

- **`private Coordinate getIntermediatePoint(Coordinate first, Coordinate second)`**: 
  - **Описание**: Вычисляет координаты промежуточной точки между двумя клетками.
  - **Параметры**:
    - `first`: первая клетка (объект класса `Coordinate`).
    - `second`: вторая клетка (объект класса `Coordinate`).
  - **Возвращает**: Координаты промежуточной клетки (объект класса `Coordinate`).
