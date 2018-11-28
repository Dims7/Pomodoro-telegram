package ru.urfu;

public class Strings
{
    static final String MESSAGE_WHEN_WORK_STARTED = "Иди работать";
    static final String MESSAGE_WHEN_LONG_REST_STARTED = "Пора отдыхать (длинный перерыв)";
    static final String MESSAGE_WHEN_SHORT_REST_STARTED = "Пора отдыхать (короткий перерыв)";
    static final String MESSAGE_WHEN_POMODORO_STARTED = "Помодоро запущен, приступайте к работе";
    static final String MESSAGE_WHEN_POMODORO_RESTARTED = "Помодоро перезапущен";
    static final String MESSAGE_WHEN_POMODORO_STOPPED = "Помодоро остановлен";
    static final String MESSAGE_WHEN_POMODORO_NOT_FOUND = "Нет запущенных помодоров";
    static final String MESSAGE_HELP = new StringBuilder()
            .append("Метод «Помидора» - техника управления временем, предложенная Франческо Чирилло в конце 1980-х. \n")
            .append("Техника предполагает разбиение задач на 25-минутные периоды, называемые «помидоры»\n, " +
                    "сопровождаемые короткими перерывами.\n")
            .append("Базовые принципы:\n")
            .append("1. Определитесь с задачей, которую будете выполнять.\n")
            .append("2. Поставьте помидор (таймер) на 25 минут.\n")
            .append("3. Работайте, ни на что не отвлекаясь, пока таймер не прозвонит. Если что-то отвлекающее возникло у вас в голове, запишите это и немедленно возвращайтесь к работе.\n")
            .append("4. Сделайте короткий перерыв (5 минут).\n")
            .append("5. После каждого 4-го «помидора» сделайте длинный перерыв (25 минут).\n")
            .append("\n")
            .append("Для запуска бота отправьте START\n")
            .append("Для остановки отправьте STOP\n")
            .append("Для вызова статистики отправьте STATISTIC\n")
            .toString();
    static final String MESSAGE_WRONG_COMMAND = "Несуществующая команда. Для вызова справки введите HELP.";
    static final String MESSAGE_STATISTIC = "Количество законченных рабочих этапов: ";
}
