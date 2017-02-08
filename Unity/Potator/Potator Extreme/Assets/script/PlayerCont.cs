using UnityEngine;
using System.Collections;

public class PlayerCont : MonoBehaviour {

    private Animator animator;
    private SpriteRenderer spr;
    private bool derecha;
    public GameObject gameManager;

	// Use this for initialization
	void Start () {
        derecha = true;
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
                derecha = true;
                spr.flipX = true;
            }
            else
            {
                derecha = false;
                spr.flipX = false;
            }
        }
        else
        {
            if (Input.GetButton("Fire1"))
            {
                animator.Play("Fire");
                gameManager.GetComponent<GameManager>().disparar(derecha,transform.position.x,transform.position.y);
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
