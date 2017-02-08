using UnityEngine;
using System.Collections;

public class BestiaController : MonoBehaviour {

    public int hits;
    private Animator animator;
    public bool muerto;

	// Use this for initialization
	void Start () {
        hits = 0;
        animator = this.gameObject.GetComponent<Animator>();
        muerto = false;

    }
	
	// Update is called once per frame
	void Update () {
        if (!muerto)
        {
            animator.Play("Walk");
        }
        if(hits > 60)
        {
            transform.localScale = new Vector3(1.0f, 1.0f, 1.0f);
            animator.Play("Die");
            muerto = true;
        }

	}
}
