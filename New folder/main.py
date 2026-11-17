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

    score_result = calculate_wellness_score(today_data)
    baseline_result = calculate_personal_baseline(history_data, today_data)
    patterns_result = detect_patterns(history_data, today_data)
    trend_result = forecast_lifestyle_trend(history_data)
    insight_result = generate_ai_insight_and_action(today_data, score_result)

    return jsonify({
        "status": "success",
        "wellness_score": score_result,
        "baseline_comparison": baseline_result,
        "patterns_detected": patterns_result,
        "trend_forecast": trend_result,
        "ai_insight": insight_result["ai_insight"],
        "one_small_change": insight_result["one_small_change"]
    })

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)