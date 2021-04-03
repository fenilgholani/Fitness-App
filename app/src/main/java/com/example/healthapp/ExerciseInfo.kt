package com.example.healthapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ExerciseInfo: AppCompatActivity() {

    private var exerciseName: String?=null
    private var bigTextView: TextView? = null
    private var titleTextView: TextView? = null
    private var infotextView: TextView? = null
    private var imgView: ImageView?=null

//    init{
//        this.exerciseName = exerciseName
//        Toast.makeText(this, "You clicked on $exerciseName", Toast.LENGTH_SHORT).show()
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_info)


        var intent = intent
        var exerciseName = intent.getStringExtra("exerciseName")

        bigTextView = findViewById(R.id.exerciseName)
        imgView = findViewById(R.id.imageView)
        titleTextView = findViewById(R.id.title)
        infotextView = findViewById(R.id.instruction)

        bigTextView!!.text = exerciseName

        if(exerciseName == "Planks"){

            titleTextView!!.text =  "How to Do the Basic Plank"

            imgView!!.setImageResource(R.drawable.plank)

            infotextView!!.text =
                    "1. Get in the pushup position, only put your forearms on the ground instead of your hands. Your elbows should line up directly underneath your shoulders. Toes on the ground.\n" +
                    "2. Squeeze your glutes and tighten your abdominals.\n" +
                    "3. Keep a neutral neck and spine.\n" +
                    "4. Create a straight, strong line from head to toes – a plank, if you will.\n" +
                    "5. Hold that position."
        }

        else if(exerciseName == "Pull up"){

            titleTextView!!.text =  "How to Do the Basic Pull up"

            imgView!!.setImageResource(R.drawable.pullup)

            infotextView!!.text =
                "1. Start by standing directly below a pull-up bar. Place your hands in an overhand grip (palms facing away from your body) with your hands slightly further than shoulder-with apart. If you can’t reach the bar from standing on the floor, you can place a box beneath you and stand on that instead. Once your hands are holding onto the bar, you’re in your starting position.\n" +
                        "2. Inhale, then exhale. Lift your feet up from the floor or box so that you’re hanging from the bar, and engage your core by pulling your belly button in toward your spine. Pull your shoulders back and down.\n" +
                        "3. Engaging the muscles in your arms and back, bend your elbows and raise your upper body up toward the bar until your chin is over the bar. You can imagine bringing your elbows toward your hips if that makes the movement easier. As you move, avoid swinging your legs around or shrugging your shoulders up. You want to make sure your shoulder blades remain back and down throughout the exercise.\n" +
                        "4. At the top of the movement, inhale. Then extend your elbows and lower your body back down to the starting position. "
        }

        else if(exerciseName == "Push up"){

            titleTextView!!.text =  "How to Do the Basic Push up"

            imgView!!.setImageResource(R.drawable.pushup)

            infotextView!!.text =
                "1. Begin with your chest and stomach flat on the floor. Your legs should be straight out behind you and your palms should be at chest level with the arms bent out at a 45-degree angle.\n" +
                        "2. Exhale as you push from your hands and heels, bringing your torso, chest, and thighs off the ground.\n" +
                        "3. Pause for a second in the plank position — keep your core engaged.\n" +
                        "4. Inhale as you slowly lower back to your starting position."
        }

        else if(exerciseName == "Sit up"){

            titleTextView!!.text =  "How to Do the Basic Sit up"

            imgView!!.setImageResource(R.drawable.situp)

            infotextView!!.text =
                "1. Sit on a mat. Keep your legs hip-width apart, feet flat on the floor, hands placed on the sides of your thighs, and spine straight.\n" +
                        "2. Roll back down on the mat until your upper back touches the mat, and your knees are pointing toward the ceiling.\n" +
                        "3. When you lie down, your lower back is curved, which known as anterior tilt. And this is dangerous for your spine while doing sit-ups. Change the anterior tilt to posterior tilt by pushing your pelvic region up so that your lower back is touching the mat completely.\n" +
                        "4. Engage your core. Keep your neck in line with the spine, look diagonally up at the ceiling, and inhale.\n" +
                        "5. Exhale and use your core muscles to sit up. As you do so, your hands will slide up from your thighs to your knees. Keep your shoulders relaxed.\n" +
                        "6. Inhale and slowly roll back down to the starting position. Make sure your lower back is completely flat on the mat.\n" +
                        "7. Again, exhale and sit up.\n"
        }

        else if(exerciseName == "Squats"){

            titleTextView!!.text =  "How to Do the Basic Squat"

            imgView!!.setImageResource(R.drawable.squat)

            infotextView!!.text =
                "1. Place a chair behind you and begin standing facing away from the chair, with both feet planted on the floor hip-width apart.\n" +
                        "\n" +
                        "2. Inhale and engage your core. Looking straight ahead, bend at both the hips and knees, ensuring that your knees remain in line with your toes. Continue bending, driving your hips back and bending your knees until you are able to lightly sit on the chair behind you, ensuring that you maintain a proud chest and avoid hunching throughout the movement. \n" +
                        "\n" +
                        "3. Exhale as you push through your heels to mid foot and extend your hips and knee simultaneously to return to the starting position. You should feel the tension in your glutes, quads and hamstrings."
        }


    }



}
