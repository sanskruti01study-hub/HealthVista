from wellness_score import calculate_wellness_score

def forecast_lifestyle_trend(history, today_score):
    """
    Forecasts the 7-day wellness trend based on recent score momentum.
    """
    if len(history) < 2:
        return {
            "direction": "Stable",
            "projected_7_day_score": today_score,
            "summary": "Log at least 3 days of data to unlock full trend predictions."
        }

    past_scores = [calculate_wellness_score(day)["score"] for day in history[-3:]]
    avg_past_score = sum(past_scores) / len(past_scores)
    diff = today_score - avg_past_score

    projected = max(0, min(100, round(today_score + (diff * 0.5))))

    if diff >= 5:
        direction = "Improving"
        summary = f"Upward momentum! Keep this pace and your score could reach ~{projected}/100 next week."
    elif diff <= -5:
        direction = "Declining"
        summary = f"Your recent habits are dipping. Without adjustment, your score may drop toward ~{projected}/100."
    else:
        direction = "Steady"
        summary = f"Your routine is consistent, projecting a steady score around {projected}/100 next week."

    return {
        "direction": direction,
        "projected_7_day_score": projected,
        "summary": summary
    }