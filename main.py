<<<<<<< HEAD
from flask import Flask, request, jsonify
from wellness_score import calculate_wellness_score
from baseline import calculate_personal_baseline
from pattern_detection import detect_patterns
from trend_forecast import forecast_lifestyle_trend
from ai_insight import generate_ai_insight_and_action

app = Flask(__name__)

@app.route("/", methods=["GET"])
def home():
    return jsonify({"status": "Health Vista AI Engine is running!"})

@app.route("/analyze", methods=["POST"])
def analyze_health():
    payload = request.get_json()
    if not payload or "today" not in payload:
        return jsonify({"error": "Please provide 'today' health data in JSON format."}), 400

    today_data = payload["today"]
    history_data = payload.get("history", [])

    # Step 1: Wellness Score
    score_result = calculate_wellness_score(today_data)

    # Step 2: Personal Baseline
    baseline_result = calculate_personal_baseline(history_data, today_data)

    # Step 3: Pattern Detection
    patterns_result = detect_patterns(history_data, today_data)

    # Step 4: Trend Forecast
    trend_result = forecast_lifestyle_trend(history_data, score_result["score"])

    # Step 5: AI Insight & One Small Change
    insight_result = generate_ai_insight_and_action(
        today_data, score_result, patterns_result, trend_result
    )

    return jsonify({
        "wellness_score": score_result,
        "personal_baseline": baseline_result,
        "patterns_detected": patterns_result,
        "trend_forecast": trend_result,
        "ai_insight": insight_result["ai_insight"],
        "one_small_change": insight_result["one_small_change"]
    })

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
=======
import json
from wellness_score import calculate_wellness_score

def main():
    try:
        with open("data/sample_data.json", "r") as f:
            user_data = json.load(f)
    except FileNotFoundError:
        print("Sample data file not found!")
        return

    score = calculate_wellness_score(user_data)
    print(f"--- Health Vista AI Engine ---")
    print(f"Calculated Wellness Score: {score} / 100")

if __name__ == "__main__":
    main()
>>>>>>> 358c14c368c3c7af094a09200031ff8a9d0d74f2
