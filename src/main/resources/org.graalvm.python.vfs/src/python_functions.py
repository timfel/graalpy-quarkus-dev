import pygal


def greet(name):
    pie_chart = pygal.Pie()
    pie_chart.title = f'Languages you can use with {name}'
    languages = ["Java", "Kotlin", "Scala"]
    # languages.append("Python")
    for l in languages:
        pie_chart.add(l, 100 / len(languages))
    return pie_chart.render(is_unicode=True)
