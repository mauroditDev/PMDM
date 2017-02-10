using UnityEngine;
using System.Collections;

public class PlayerCont : MonoBehaviour {

    private Animator animator;
    private SpriteRenderer spr;
    private bool derecha;
    public GameObject gameManager;
    public bool suelo;
    public float velocidad;
    public float salto;
    private Rigidbody rb;
    public float tolerancia;
    public float lastY;

	// Use this for initialization
	void Start () {
        derecha = true;
        animator = this.gameObject.GetComponent<Animator>();
        spr = this.gameObject.GetComponent<SpriteRenderer>();
        suelo = true;
        rb = this.gameObject.GetComponent<Rigidbody>();
	}
	
	// Update is called once per frame
	void Update () {
        Debug.Log(this.transform.position.y);
        if(this.transform.position.y-lastY > (tolerancia*(-1)) && this.transform.position.y - lastY < tolerancia)
        {
            suelo = true;
        }
        else
        {
            suelo = false;
        }

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
                        if (suelo)
                        {
                            rb.AddForce(new Vector3(0,1) * salto);
                            animator.Play("Jump");
                            suelo = false;
                        }
                        else
                        {
                            Debug.Log("no saltes");
                        }
                        
                    }
                    else
                    {
                        animator.Play("Idle");
                    }
                }
            }
        }

        if (suelo)
        {
            rb.AddForce(new Vector3(1, 1) * Input.GetAxis("Horizontal")* velocidad * Time.deltaTime);
        }
        lastY = this.transform.position.y;
	}

    

}
