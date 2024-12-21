import java.util.*;
import java.io.*;
import java.text.*;
import java.util.regex.Pattern;

public class AdvancedMentalHealthChatbot {
    
    private static Map<String, String[]> responses = new HashMap<>();
    private static String userName = "User";
    private static int userAge = 0;
    private static BufferedWriter feedbackWriter;
    private static List<String> moodHistory = new ArrayList<>();
    private static Map<String, String> userEmotions = new HashMap<>();
    private static Map<String, String> userPreferences = new HashMap<>();
    private static List<String> journalingPrompts = new ArrayList<>();
    private static List<String> mindfulnessExercises = new ArrayList<>();
    private static List<String> copingMechanisms = new ArrayList<>();
    private static List<String> selfCareTips = new ArrayList<>();
    private static List<String> affirmations = new ArrayList<>();
    private static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        initializeResponses();
        initializeJournalingPrompts();
        initializeMindfulnessExercises();
        initializeCopingMechanisms();
        initializeSelfCareTips();
        initializeAffirmations();
        
        try {
            feedbackWriter = new BufferedWriter(new FileWriter("feedback.txt", true));
        } catch (IOException e) {
            System.err.println("Error initializing feedback log.");
        }
        
        System.out.println("Welcome to the Advanced Mental Health Support Chatbot.");
        System.out.println("What's your name?");
        userName = scanner.nextLine();
        System.out.println("How old are you?");
        userAge = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Hello, " + userName + "! How can I assist you today?");
        
        System.out.println("Type 'exit' to end the conversation or 'history' to see your chat history.");
        
        while (true) {
            System.out.print(userName + ": ");
            String userInput = scanner.nextLine();
            
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye! Take care of yourself.");
                collectFeedback(scanner);
                break;
            }
            
            if (userInput.equalsIgnoreCase("history")) {
                printChatHistory();
                continue;
            }
            
            String botResponse = getResponse(userInput);
            System.out.println("Bot: " + botResponse);
            
            logConversation(userInput, botResponse);
            
            trackUserMood(userInput, scanner);
            
            providePersonalizedAdvice(userInput);
            
            timeBasedGreeting();
            
            checkForEmotions(userInput);
            
            if (userInput.toLowerCase().contains("therapy") || userInput.toLowerCase().contains("help")) {
                provideTherapeuticTechniques();
            }
            
            suggestCopingMechanisms(userInput);
            
            shareJournalingPrompt(userInput);
            
            practiceMindfulness(userInput);
            
            provideSleepAdvice(userInput);
            
            trackUserProgress(userInput);
            
            provideAffirmations(userInput);
            
            suggestSelfCareTips(userInput);
            
            offerGuidedMeditation(userInput);
        }
        
        scanner.close();
    }

    private static void initializeResponses() {
        responses.put("hi", new String[] { "Hello! How are you feeling today?", "Hi! I'm here for you." });
        responses.put("help", new String[] { "I'm here to listen. How can I assist you?", "You can talk to me anytime. How are you feeling?" });
        responses.put("sad", new String[] { "I'm sorry you're feeling this way. Do you want to talk about it?", "It's okay to feel sad sometimes. What's on your mind?" });
        responses.put("happy", new String[] { "I'm glad you're feeling happy! What's making you feel this way?", "That's great to hear! Keep enjoying that feeling." });
        responses.put("stress", new String[] { "Stress can be tough. Do you want to talk about what's stressing you?", "It might help to take a break. What's going on?" });
        responses.put("lonely", new String[] { "I'm sorry you're feeling lonely. I'm here for you.", "Sometimes loneliness can be tough. Would you like to talk about it?" });
        responses.put("anxiety", new String[] { "Anxiety can be difficult. Want to talk about it?", "It's okay to feel anxious sometimes. Let's discuss what's making you feel this way." });
        responses.put("depressed", new String[] { "I'm really sorry you're feeling depressed. Do you want to talk about it?", "Depression can be overwhelming. I'm here to listen." });
        responses.put("bye", new String[] { "Goodbye! I'm always here if you need to talk.", "Take care. I'm here if you need me." });
        responses.put("angry", new String[] { "It seems like you're feeling angry. Would you like to share what's on your mind?", "Anger can be intense. Let's explore why you're feeling this way." });
        responses.put("bored", new String[] { "Boredom can feel frustrating. Want to explore something new?", "It can be tough to feel bored. Would you like suggestions to distract yourself?" });
        responses.put("good morning", new String[] { "Good morning! How are you today?", "Good morning, " + userName + "! How's your mood?" });
        responses.put("good evening", new String[] { "Good evening! How are you feeling?", "Good evening, " + userName + "! What's on your mind?" });
    }

    private static void initializeJournalingPrompts() {
        journalingPrompts.add("Write about a time you felt really proud of yourself.");
        journalingPrompts.add("What are three things you are grateful for today?");
        journalingPrompts.add("Write about a moment in your life that changed you for the better.");
        journalingPrompts.add("Describe your ideal day. What would it look like?");
        journalingPrompts.add("Write about a challenge you've overcome and what you learned from it.");
    }

    private static void initializeMindfulnessExercises() {
        mindfulnessExercises.add("Focus on your breath for five minutes, gently bringing your mind back when it wanders.");
        mindfulnessExercises.add("Find a comfortable position, close your eyes, and imagine a peaceful place. Engage all your senses.");
        mindfulnessExercises.add("Take three deep breaths. As you breathe in, count to four; as you breathe out, count to four.");
        mindfulnessExercises.add("Focus on each part of your body, starting with your toes and moving up to your head, noticing any sensations.");
        mindfulnessExercises.add("Sit quietly and observe your thoughts without judgment. Allow them to come and go without attachment.");
    }

    private static void initializeCopingMechanisms() {
        copingMechanisms.add("Practice mindfulness and meditation.");
        copingMechanisms.add("Try journaling your thoughts.");
        copingMechanisms.add("Exercise, even a short walk, can help.");
        copingMechanisms.add("Talk to a friend or therapist about how you feel.");
        copingMechanisms.add("Try grounding exercises, like the 5-4-3-2-1 method.");
        copingMechanisms.add("Take a break and listen to music you enjoy.");
        copingMechanisms.add("Do something creative, like drawing, painting, or writing.");
        copingMechanisms.add("Practice progressive muscle relaxation (PMR).");
    }

    private static void initializeSelfCareTips() {
        selfCareTips.add("Drink plenty of water throughout the day.");
        selfCareTips.add("Try to get at least 7-8 hours of sleep each night.");
        selfCareTips.add("Take short breaks during work or study to refresh yourself.");
        selfCareTips.add("Avoid negative self-talk and replace it with positive affirmations.");
        selfCareTips.add("Spend time with loved ones to boost your mood.");
        selfCareTips.add("Engage in a hobby or something that brings you joy.");
        selfCareTips.add("Exercise regularly, even if it's just a short walk.");
    }

    private static void initializeAffirmations() {
        affirmations.add("I am enough just as I am.");
        affirmations.add("I choose to embrace positivity in my life.");
        affirmations.add("I have the strength to overcome challenges.");
        affirmations.add("I am proud of the progress I’ve made.");
        affirmations.add("I trust myself and my abilities.");
        affirmations.add("I am worthy of love and happiness.");
        affirmations.add("I am capable of achieving my goals.");
    }

    private static String getResponse(String input) {
        for (String key : responses.keySet()) {
            if (Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(input).find()) {
                String[] possibleResponses = responses.get(key);
                return possibleResponses[random.nextInt(possibleResponses.length)];
            }
        }
        return "I'm here to listen. Please tell me more about how you're feeling.";
    }

    private static void logConversation(String userInput, String botResponse) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("conversation_log.txt", true))) {
            writer.write("User: " + userInput);
            writer.newLine();
            writer.write("Bot: " + botResponse);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file.");
        }
    }

    private static void collectFeedback(Scanner scanner) {
        System.out.println("How was your experience with the chatbot? (Rate from 1-5)");
        String feedback = scanner.nextLine();
        try {
            feedbackWriter.write("Feedback from " + userName + ": " + feedback);
            feedbackWriter.newLine();
            feedbackWriter.close();
        } catch (IOException e) {
            System.err.println("Error writing feedback.");
        }
    }

    private static void trackUserMood(String userInput, Scanner scanner) {
        if (userInput.toLowerCase().contains("mood")) {
            System.out.println("Please describe your current mood (e.g., happy, sad, anxious):");
            String mood = scanner.nextLine();
            moodHistory.add("Mood recorded at " + getCurrentTime() + ": " + mood);
            System.out.println("Your mood has been recorded.");
        }
    }

    private static void printChatHistory() {
        if (moodHistory.isEmpty()) {
            System.out.println("No chat history available.");
        } else {
            for (String entry : moodHistory) {
                System.out.println(entry);
            }
        }
    }

    private static void providePersonalizedAdvice(String input) {
        if (input.toLowerCase().contains("stress") || input.toLowerCase().contains("anxiety")) {
            System.out.println("Bot: Here are some personalized tips for dealing with stress and anxiety:");
            suggestCopingMechanisms();
        } else if (input.toLowerCase().contains("depressed")) {
            System.out.println("Bot: Remember, it's okay to ask for help. Here are some ways to cope with depression:");
            suggestCopingMechanisms();
        }
    }

    private static void suggestCopingMechanisms(String input) {
        Collections.shuffle(copingMechanisms);
        for (int i = 0; i < 3; i++) {
            System.out.println("- " + copingMechanisms.get(i));
        }
    }

    private static void provideTherapeuticTechniques() {
        System.out.println("Here are some therapeutic exercises you might find helpful:");
        List<String> techniques = Arrays.asList(
            "CBT Exercise: Write down your negative thoughts and challenge them with positive alternatives.",
            "Journaling Prompt: Write about a moment when you felt truly happy.",
            "Mindfulness Exercise: Take five deep breaths and focus on how your body feels.",
            "Gratitude Exercise: List three things you're grateful for today."
        );
        Collections.shuffle(techniques);
        for (int i = 0; i < 2; i++) {
            System.out.println("- " + techniques.get(i));
        }
    }

    private static void timeBasedGreeting() {
        int hour = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
        
        if (hour < 12) {
            System.out.println("Good morning, " + userName + "! How are you feeling today?");
        } else if (hour < 18) {
            System.out.println("Good afternoon, " + userName + "! How's your day going?");
        } else {
            System.out.println("Good evening, " + userName + "! I hope you're feeling okay.");
        }
    }

    private static void checkForEmotions(String input) {
        String[] emotions = { "sad", "happy", "angry", "anxious", "lonely", "bored" };
        
        for (String emotion : emotions) {
            if (input.toLowerCase().contains(emotion)) {
                userEmotions.put(getCurrentTime(), emotion);
                System.out.println("Bot: I see you're feeling " + emotion + ". Would you like to talk more about it?");
                break;
            }
        }
    }

    private static void shareJournalingPrompt(String input) {
        if (input.toLowerCase().contains("journal") || input.toLowerCase().contains("write")) {
            System.out.println("Bot: Here's a journaling prompt for you: ");
            System.out.println(journalingPrompts.get(random.nextInt(journalingPrompts.size())));
        }
    }

    private static void practiceMindfulness(String input) {
        if (input.toLowerCase().contains("mindful") || input.toLowerCase().contains("relax")) {
            System.out.println("Bot: Let's do a mindfulness exercise together. ");
            System.out.println(mindfulnessExercises.get(random.nextInt(mindfulnessExercises.size())));
        }
    }

    private static void provideSleepAdvice(String input) {
        if (input.toLowerCase().contains("sleep") || input.toLowerCase().contains("tired")) {
            System.out.println("Bot: Here are some tips to improve your sleep quality:");
            List<String> sleepTips = Arrays.asList(
                "Avoid caffeine late in the day.",
                "Establish a consistent bedtime routine.",
                "Try relaxation exercises before bed.",
                "Keep your bedroom cool and quiet.",
                "Limit screen time at least one hour before sleep
