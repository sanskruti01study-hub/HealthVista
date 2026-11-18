
from flask import Flask, request, jsonify

from wellness_score import calculate_wellness_score
from baseline import calculate_personal_baseline
from pattern_detection import detect_patterns
from trend_forecast import forecast_lifestyle_trend
from ai_insight import generate_ai_insight_and_action

app = Flask(__name__)


@app.route("/", methods=["GET"])
def home():
    return jsonify({
        "status": "HealthVista AI Engine is running!"
    })


@app.route("/analyze", methods=["POST"])
def analyze_health():
    payload = request.get_json(silent=True)

    if not payload or "today" not in payload:
        return jsonify({
            "error": "Please provide today's health data."
        }), 400

    today_data = payload["today"]
    history = payload.get("history", [])

    if not isinstance(today_data, dict):
        return jsonify({"error": "'today' must be an object."}), 400

    if not isinstance(history, list):
        return jsonify({"error": "'history' must be a list."}), 400

    try:
        score_result = calculate_wellness_score(today_data)

        baseline_result = calculate_personal_baseline(
            history, today_data
        )

        patterns_result = detect_patterns(
            history, today_data
        )

        trend_result = forecast_lifestyle_trend(history)

        insight_result = generate_ai_insight_and_action(
            today_data, score_result
        )

        return jsonify({
            "wellness": score_result,
            "baseline": baseline_result,
            "patterns": patterns_result,
            "trend": trend_result,
            "insight": insight_result
        })

    except (TypeError, ValueError, KeyError) as error:
        return jsonify({
            "error": "Invalid health data.",
            "details": str(error)
        }), 400

    except Exception:
        app.logger.exception("Health analysis failed")
        return jsonify({
            "error": "The analysis could not be completed."
        }), 500


if __name__ == "__main__":
    app.run(
        host="0.0.0.0",
        port=5000,
        debug=False
    )
