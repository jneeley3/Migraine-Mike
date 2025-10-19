import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
public class AIStuff {
    public static String escapeJson(String input) {
    return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
    public static String buildJsonPayload(String body, double temperature) {
        StringBuilder sb = new StringBuilder();
        sb.append("{")
        .append("\"messages\": [],")
        .append("\"prompt\": \"")
        .append(body).append("\",")
        .append("\"temperature\": ").append(0.5).append(",")
        .append("\"is_stream\": false")
        .append("}");
    return sb.toString();
    }

  // Sends an asynchronous HTTP POST request to the specified API endpoint with the given body and headers.
  public static String sendPostAsync(String body, double temperature, String apiToken) {
    HttpClient client = HttpClient.newHttpClient(); // Intializes a new HTTP client to send the request.
    String input = buildJsonPayload(body, temperature); // Builds the JSON payload for the request body.
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://kronoslabs.org/api/chat/hermes"))
        .header("Content-Type", "application/json")
        .header("X-API-Key", apiToken)
        .POST(BodyPublishers.ofString(input))
        .build();
    try {
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    String[] responseArray = response.body().split("\"");
    for(int i = 0; i < responseArray.length; i++) {
        System.out.println("Part " + i + ": " + responseArray[i]);
    }
    String returnVal = responseArray[27];
    returnVal = returnVal.replaceAll("\n", "").replaceAll("\n\n","");
    return responseArray[27];
    } catch (IOException e) {
        System.err.println("I/O error: " + e.getMessage());
        e.printStackTrace();
    } catch (InterruptedException e) {
        System.err.println("Request interrupted: " + e.getMessage());
        Thread.currentThread().interrupt(); // restore interrupt status
    }
    return null;
}
}