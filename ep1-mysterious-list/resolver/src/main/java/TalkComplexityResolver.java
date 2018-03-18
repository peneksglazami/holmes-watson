public class TalkComplexityResolver {

    public static String resolveTalkComplexity(String title) {
        if (title.contains("Kotlin")) {
            return "Хардкор. Сложный низкоуровневый доклад, требующий от слушателя знаний технологии.";
        } else if (title.contains("Spring")) {
            return "Для практикующих инженеров";
        } else {
            return "Введение в технологию";
        }
    }
}
