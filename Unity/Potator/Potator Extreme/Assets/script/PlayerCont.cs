using UnityEngine;
using System.Collections;

public class PlayerCont : MonoBehaviour {

    private Animator animator;
    private SpriteRenderer spr;

	// Use this for initialization
	void Start () {
        animator = this.gameObject.GetComponent<Animator>();
        spr = this.gameObject.GetComponent<SpriteRenderer>();
	}
	
	// Update is called once per frame
	void Update () {
        
        if (Input.GetAxis("Horizontal") != 0)
        {
            animator.Play("WalkRt");
            if (Input.GetAxis("Horizontal") < 0)
            {
                spr.flipX = true;
            }
            else
            {
                spr.flipX = false;
            }
        }
        else
        {
            if (Input.GetButton("Fire1"))
            {
                animator.Play("Fire");
            }
            else
            {
                if (Input.GetAxis("Vertical") < 0)
                {
                    animator.Play("Duck");
                }
                else
                {
                    if (Input.GetAxis("Vertical") > 0)
                    {
                        animator.Play("Jump");
                    }
                    else
                    {
                        animator.Play("Idle");
                    }
                }
            }
        }
        
        
	}
}
