def forecast_lifestyle_trend(history):
    if len(history) < 3:
        return {"trend": "Insufficient data to forecast trends."}

    recent_steps = [h.get("steps", 0) for h in history[-3:]]
    if recent_steps[-1] > recent_steps[0]:
        return {"trend": "Improving", "message": "Your physical activity is showing an upward trend!"}
    else:
        return {"trend": "Stable/Declining", "message": "Consider increasing your daily step count to reverse the trend."}