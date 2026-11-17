def generate_ai_insight_and_action(today_data, score_result):
    score = score_result["wellness_score"]
    
    if score >= 80:
        insight = "You are maintaining a fantastic, balanced lifestyle today!"
        change = "Keep up your current routine and ensure you stay hydrated."
    elif score >= 60:
        insight = "You are doing okay, but certain metrics like screen time or sleep can be optimized."
        change = "Try to reduce your screen time by 30 minutes before bed tonight."
    else:
        insight = "Your wellness score indicates high stress and low activity today."
        change = "Take a short 15-minute evening walk and cut down on sugary snacks."

    return {
        "ai_insight": insight,
        "one_small_change": change
    }